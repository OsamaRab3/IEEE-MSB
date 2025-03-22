const util = require('util');
const fs = require('fs')
const writeFilePromise = util.promisify(fs.writeFile);
const config = require('./config')


const log = async (message) => {
    const timestamp = new Date().toISOString();
    const logMessage = `[${timestamp}] ${message}\n`;
    
    console.log(message);
    
    try {
      await writeFilePromise(
        config.logFile,
        logMessage,
        { flag: 'a' }
      );
    } catch (err) {
      console.error('Failed to write to log file:', err);
    }
  };


module.exports = log;