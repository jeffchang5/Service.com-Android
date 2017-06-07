// Mock database for testing users 
const _ = require('lodash');

const address = [
    {
        id: 1,
        street: 'One Infinite Loop',
        suite: '',
        city: 'Cupertino',
        state: 'CA',
        zip: '95014',
    },
    {
        id: 2,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
    {
        id: 3,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
    {
        id: 4,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
    {
        id: 5,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
        {
        id: 6,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
        {
        id: 7,
        street: '1600 Amphitheatre Parkway',
        suite: '',
        city: 'Mountain View',
        state: 'CA',
        zip: '94043',
    },
];

module.exports = [
    {
        id: 1,
        name: 'Jeffrey Chang',
        email: 'changjef@umich.edu',
        phone: '2222222222',
        address: _.find(address, { 'id': 1 }),
        profile_pic: "http://localhost:8080/static/1.jpg",
        role: 'consumer'
    },
    {
        id: 2,
        name: 'Paul Proctor',
        email: 'paulsemail@service.com',
        phone: '1111111111',
        address: _.find(address, { 'id': 2 }),
        profile_pic: "http://localhost:8080/static/2.jpg",
        role: 'pro'
    },
    {
        id: 3,
        name: 'Harry Potter',
        email: 'potter.harry@hogwarts.edu',
        phone: '1234567890',
        address: _.find(address, { 'id': 3 }),
        profile_pic: "http://localhost:8080/static/3.jpg",
        role: 'consumer'
    },
    {
        id: 4,
        name: 'Hermione Granger',
        email: 'granger.hermione@hogwarts.edu',
        phone: '1111111111',
        address: _.find(address, { 'id': 4 }),
        profile_pic: "http://localhost:8080/static/4.jpg",
        role: 'consumer'
    },
    {
        id: 5,
        name: 'Ron Weasley',
        email: 'weasley.ron@hogwarts.edu',
        phone: '1111111111',
        address: _.find(address, { 'id': 5 }),
        profile_pic: "http://localhost:8080/static/5.jpg",
        role: 'consumer'
    },
    {
        id: 6,
        name: 'Draco Malfoy',
        email: 'malfoy.draco@hogwarts.edu',
        phone: '1111111111',
        address: _.find(address, { 'id': 6 }),
        profile_pic: "http://localhost:8080/static/6.jpg",
        role: 'pro'
    },
    {
        id: 7,
        name: 'Albus Dumbledore',
        email: 'dumbledore.albus@hogwarts.edu',
        phone: '1111111111',
        address: _.find(address, { 'id': 7 }),
        profile_pic: "http://localhost:8080/static/7.jpg",
        role: 'consumer'
    },

];