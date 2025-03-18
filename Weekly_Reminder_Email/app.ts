
import exprss from 'express'

const app = exprss();


app.get('/',(req,res)=>{
    res.send("Welcome in weekly reminder email")
})


export = app; 

