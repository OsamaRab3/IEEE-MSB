
const { Storage } = require('./data');
const express = require('express');
const crypto = require('crypto');

const data = new Storage();
const app = express();

app.use(express.json());


const hash = (url) => {
    const hash = crypto.createHash('md5').update(url).digest('base64');
    return hash.substring(0, 6);
};

app.post('/short', (req, res) => {
    const { url } = req.body;
    
    if (!url) {
        return res.status(400).json({ error: 'URL is required' });
    }
    
    const shortCode = hash(url);
    data.set(shortCode, url);
    
    res.json({ shortUrl: `${shortCode}` });
});

app.get('/:shortCode', (req, res) => {
    const { shortCode } = req.params;
    const originalUrl = data.get(shortCode);
    
    if (!originalUrl) {
        return res.status(404).json({ error: 'Short URL not found' });
    }
    
    res.send(originalUrl);
});

app.listen(3000, () => {
    console.log('Server running on http://localhost:3000');
});
