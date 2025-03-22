
const path = require('path')
const config = {
  database: '',
  user: '',
  password: '',
  backupDir: path.join(__dirname, 'backups'),
  logFile: path.join(__dirname, 'backup.log'),
  retentionDays: 7
};



module.exports = config;