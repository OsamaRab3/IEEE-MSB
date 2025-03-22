
const { exec } = require('child_process');
const fs = require('fs');
const path = require('path');
const util = require('util');
const zlib = require('zlib');

const config = require('./config')
const log = require('./utils')

const execPromise = util.promisify(exec);
const writeFilePromise = util.promisify(fs.writeFile);
const readFilePromise = util.promisify(fs.readFile);
const gzipPromise = util.promisify(zlib.gzip);
const gunzipPromise = util.promisify(zlib.gunzip);

if (!fs.existsSync(config.backupDir)) {
  fs.mkdirSync(config.backupDir, { recursive: true });
}


const getBackupFilename = () => {
  const now = new Date();
  const timestamp = now.toISOString().replace(/:/g, '-').replace(/\..+/, '');
  return `backup_${config.database}_${timestamp}.sql`;
};

const performBackup = async () => {
  await log('Starting database backup...');
  
  const backupFile = path.join(config.backupDir, getBackupFilename());
  const compressedFile = `${backupFile}.gz`;
  
  try {
    
    const { stdout, stderr } = await execPromise(
      `mysqldump -u ${config.user} -p${config.password} ${config.database}`
    );
    
    if (stderr && !stderr.includes('Warning')) {
      throw new Error(stderr);
    }

    await writeFilePromise(backupFile, stdout);
    await log(`Backup created: ${backupFile}`);

    const data = await readFilePromise(backupFile);
    const compressed = await gzipPromise(data);
    await writeFilePromise(compressedFile, compressed);
    await log(`Backup compressed: ${compressedFile}`);

    fs.unlinkSync(backupFile);

    await cleanupOldBackups();
    
    await log('Backup completed successfully');
    return compressedFile;
  } catch (err) {
    await log(`Backup failed: ${err.message}`);
    throw err;
  }
};

const restoreBackup = async (backupFile) => {
  
  if (!backupFile) {
    throw new Error('No backup file specified');
  }
  
  if (!fs.existsSync(backupFile)) {
    throw new Error(`Backup file not found: ${backupFile}`);
  }
  
  await log(`Starting restore from: ${backupFile}`);
  
  try {
    let sqlContent;

    if (backupFile.endsWith('.gz')) {
      const compressed = await readFilePromise(backupFile);
      const decompressed = await gunzipPromise(compressed);
      sqlContent = decompressed;
      await log('Decompressed backup file');
    } else {
      sqlContent = await readFilePromise(backupFile);
    }
    

    const tempFile = path.join(config.backupDir, 'temp_restore.sql');
    await writeFilePromise(tempFile, sqlContent);
    

    const { stdout, stderr } = await execPromise(
      `mysql -u ${config.user} -p${config.password} ${config.database} < ${tempFile}`
    );
    
    if (stderr && !stderr.includes('Warning')) {
      throw new Error(stderr);
    }
    

    fs.unlinkSync(tempFile);
    
    await log('Database restored successfully');
    return true;
  } catch (err) {
    await log(`Restore failed: ${err.message}`);
    throw err;
  }
};


const cleanupOldBackups = async () => {
  try {
    const files = fs.readdirSync(config.backupDir);
    const now = new Date();
    
    for (const file of files) {
      if (!file.startsWith('backup_') || !file.endsWith('.sql.gz')) {
        continue;
      }
      
      const filePath = path.join(config.backupDir, file);
      const stats = fs.statSync(filePath);
      const fileDate = new Date(stats.mtime);
      const diffDays = (now - fileDate) / (1000 * 60 * 60 * 24);
      
      if (diffDays > config.retentionDays) {
        fs.unlinkSync(filePath);
        await log(`Deleted old backup: ${file}`);
      }
    }
  } catch (err) {
    await log(`Error cleaning up old backups: ${err.message}`);
  }
};

const main = async () => {
  const command = process.argv[2];
  const backupFile = process.argv[3];
  
  try {
    switch (command) {
      case 'backup':
        await performBackup();
        break;
      case 'restore':
        if (!backupFile) {
          console.error('Error: No backup file specified');
          console.log('Usage: node  restore path/to/backup.sql.gz');
          process.exit(1);
        }
        await restoreBackup(backupFile);
        break;
      default:
        console.log('Usage: node db-backup.js {backup|restore [backup_file]}');
        process.exit(1);
    }
  } catch (err) {
    console.error(`Error: ${err.message}`);
    process.exit(1);
  }
};

main();


const scheduleBackup = () => {
  const ONE_DAY = 24 * 60 * 60 * 1000;
  
  const now = new Date();
  const midnight = new Date(now);
  midnight.setHours(0, 0, 0, 0);
  midnight.setDate(midnight.getDate() + 1); 
  
  const timeUntilMidnight = midnight.getTime() - now.getTime();
  

  setTimeout(async () => {
    await performBackup();
    
    setInterval(performBackup, ONE_DAY);
  }, timeUntilMidnight);
  
  console.log(`Scheduled first backup at midnight (in ${Math.round(timeUntilMidnight / 1000 / 60)} minutes)`);
};

