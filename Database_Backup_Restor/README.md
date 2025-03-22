# Automatic Database Backup & Restore Script

This project provides a Node.js script to automate the backup and restoration of a MySQL database. The script is designed to create daily backups, compress them to save space, and allow easy restoration when needed. It also includes logging to track the success or failure of backup operations.

## Features

- **Automated Backups**: Schedule daily backups at midnight.
- **Compression**: Backup files are compressed using Gzip to save disk space.
- **Restoration**: Easily restore the database from a selected backup file.
- **Logging**: All backup and restore operations are logged for tracking.
- **Retention Policy**: Old backups are automatically deleted based on a configurable retention period.

## Prerequisites

- Node.js installed on your system.
- MySQL database with appropriate credentials.
- `mysqldump` and `mysql` commands available in your system's PATH.



1. Configure the database access in `config.js`:
   ```javascript
   const config = {
     database: '',         // Your database name
     user: '',            // Your database username
     password: '',       // Your database password
     backupDir: path.join(__dirname, 'backups'),       // Directory to store backups
     logFile: path.join(__dirname, 'backup.log'),     // Log file path
     retentionDays: 7                                // Number of days to keep backups
   };
   ```

## Usage

### Backup

To manually trigger a backup, run:
```bash
node index.js backup
```

### Restore

To restore the database from a backup file, run:
```bash
node index.js restore path/to/backup.sql.gz
```

### Scheduling Backups

The script includes a function to schedule daily backups at midnight. To enable this, uncomment the `scheduleBackup` function call in `index.js`:
```javascript
// Uncomment the following line to schedule daily backups
// scheduleBackup();
```

## Logs

All backup and restore operations are logged to `backup.log` in the project directory. Sample logs are included in the repository.

## Configuration

- **Backup Directory**: Set the `backupDir` in `config.js` to specify where backups should be stored.
- **Retention Policy**: Adjust the `retentionDays` in `config.js` to control how long backups are kept before being deleted.

