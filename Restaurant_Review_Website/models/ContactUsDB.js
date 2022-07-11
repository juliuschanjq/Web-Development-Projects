"use strict"

var db = require('../db-connection');
const Contact = require('../models/contact');

class ContactUsDB
{

    getAllContacts(request, respond)
    {
        var sql = "SELECT * FROM restaurant_reviews.contactus";
        db.query(sql, function(error, result)
        {
            if(error)
            {
                throw error;
            }
            else
            {
                respond.json(result);
            }
    
        })    
    }

    newContact(request, respond)
    {
        var contactObject = new Contact(null, request.body.fullname, request.body.email ,request.body.phonenumber, request.body.message);
            
        var sql = "INSERT INTO restaurant_reviews.contactus (fullname, email, phonenumber, message) VALUES(?, ?, ?, ?)";

        var values = [contactObject.getFullName(), contactObject.getEmail(), contactObject.getPhoneNumber(), contactObject.getMessage()];

        db.query(sql, values, function (error, result) 
        {
        if(error)
        {
        throw error;
        }
        else
        {
        respond.json(result);
        }
        });
    }
}
module.exports = ContactUsDB    