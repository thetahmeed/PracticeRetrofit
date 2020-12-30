





const mExpress = require('express')
const mMongoose = require('mongoose')


const mApp = mExpress()


mApp.get('/api', (req, res) => {
    res.json(
        {
            name: "Tahmeedul Islam",
            email: "tahmeed.com.bd@gmail.com",
            phone: "01700547406"
        }
    )
})


const PORT = process.env.PORT || 8080

mApp.listen(PORT, () => {
    console.log(`I am on Port ${PORT}`)
})