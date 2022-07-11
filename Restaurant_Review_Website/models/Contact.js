"use strict";

class Contact
{
    constructor(id, fullname, email, phonenumber, message)
    {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.message = message;
    }

    getId() 
    {
        return this.id;
    }
        

    setFullName(fullname) 
    {
        this.fullname = fullname;
    }

    getFullName() 
    {
        return this.fullname;
    }

    setEmail(email) 
    {
        this.email = email;
    }
    
    getEmail() 
    {
        return this.email;
    }

    setPhoneNumber(phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    getPhoneNumber()
    {
        return this.phonenumber;
    }

    setMesage(message) 
    {
        this.message = message;
    }

    getMessage()
    {
        return this.message;
    }
} 

module.exports = Contact