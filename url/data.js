

class Storage {
    constructor() {
        this.database = {};
    }
    
    set(key, url) {
        this.database[key] = url;
        return key;
    }
    
    get(shortCode) {
        return this.database[shortCode] || null;
    }
}

module.exports = {
    Storage
}
