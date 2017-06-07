// This creates a small REST server in Express to test methods

const express = require('express');
const routes = require('./routes');
const bodyParser = require('body-parser');
const PORT = process.env.PORT || 8080; 

const app = express()
app.use('/users', routes)
app.use('/static', express.static('./photos'))

app.listen(PORT, () => console.log(`Listening on ${PORT}`));
