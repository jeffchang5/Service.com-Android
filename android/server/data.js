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
];

module.exports = [
    {
        id: 1,
        name: 'Jeffrey Chang',
        email: 'changjef@umich.edu',
        phone: '2222222222',
        address: _.find(address, { 'id': 1 }),
        role: 'consumer'
    },
    {
        id: 2,
        name: 'Paul Proctor',
        email: 'changjef@umich.edu',
        phone: '1111111111',
        address: _.find(address, { 'id': 2 }),
        role: 'pro'
    }
];