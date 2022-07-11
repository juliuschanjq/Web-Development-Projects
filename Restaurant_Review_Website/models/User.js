"use strict";

class User
{
    constructor(id, userid, password, firstname, secondname, email, mobile)
    {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.firstname = firstname
        this.secondname = secondname
        this.email = email;
        this.mobile = mobile;
    }

    getId() 
    {
        return this.id;
    }
        

    setUserid(userid) 
    {
        this.userId = userid;
    }
  
    getUserId() 
    {
        return this.userid;
    }

    setPassword(password) 
    {
        this.password = password;
    }
    
    getPassword() 
    {
        return this.password;
    }

    setFirstName(firstname) 
    {
        this.firstname = firstname;
    }

    getFirstName()
    {
        return this.firstname
    }

    setSecondName(secondname)
    {
        this.secondname = secondname;
    }

    getSecondName() 
    {
        return this.secondname;
    }

    setEmail(email)
    {
        this.email = email;
    }

    getEmail() 
    {
        return this.email;
    }

    setMobile(mobile)
    {
        this.mobile = mobile;
    }    

    getMobile()
    {
        return this.mobile;
    }    

   
}

module.exports = User