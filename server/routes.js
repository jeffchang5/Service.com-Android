const express = require('express');
const _ = require('lodash');
const url = require('url');
const bodyParser = require('body-parser');
const data = require('./data');


const router = express.Router();
const urlencodedParser = bodyParser.urlencoded({ extended: false });
// Responds with user information

router.get('/', (req, res) => {
    if (Object.keys(req.query).length > 0) {
        res.send(_.find(data, { email: req.query.email } ));
    } else {
        res.send('No query for id was sent.')
    }
});

// Responds with token and descriptive status about the success/failure of database.

router.post('/login', urlencodedParser, (req, res) => {

    /* TODO
        Write logic that validates the request data with salted hashes in a production database.
    */


    if (req.body) {
        res.send(_.find(data, { email: req.body.email } ));

    } else {
        res.send({
            error: 'No user founded with email.' 
        });
    }

});
// Responds with the URL for the image for the specific user

// router.get('/photos', (req, res) => {

//     const imageURL = url.format({
//                 protocol: req.protocol,
//                 host: req.get('host'),
//     });

//     if (Object.keys(req.query).length > 0) {
//         res.send({url: `${imageURL}/static/${req.query.id}.jpg` });
//     } else {
//         res.send('No query for id was sent.');
//     }
// })

router.get('/all', (req, res) => {
    res.send(data);
})

module.exports = router;
