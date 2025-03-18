import app from './app'
import http  from 'http'
import config from './config/index'

const  server = http.createServer(app)
const PORT = config.PORT;



server.listen(PORT,()=>{
    console.log(`app listen in http://localhost:${PORT}`)
})