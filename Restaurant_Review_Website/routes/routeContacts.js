"use strict"

const ContactUsDB = require('../models/ContactUsDB');

var contactDBObject = new ContactUsDB();

function routeContacts(app)
{
    app.route('/contactus')
        .post(contactDBObject.newContact)
        .get(contactDBObject.getAllContacts);
}
module.exports = {routeContacts};