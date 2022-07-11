"use strict"

var db = require('../db-connection');
const User = require('../models/User');

class UsersDB
{
    getAllUsers(request, respond)
    {
        var sql = "SELECT * FROM restaurant_reviews.user;";
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

    registerUser(request, respond)
    {
        var UserObject = new User(null, request.body.userid, request.body.password, 
            request.body.firstname, request.body.secondname,request.body.email, request.body.mobile);
            
        var sql = "INSERT INTO restaurant_reviews.user (userid, password, firstname, secondname, email, mobile) VALUES(?, ?, ?, ?, ?, ?)";

        var values = [UserObject.getUserId(), UserObject.getPassword(), UserObject.getFirstName(), 
            UserObject.getSecondName(), UserObject.getEmail(), UserObject.getMobile()];

        db.query(sql, values, function (error, result) 
        {
        if(error)
        {
        throw error;
        }
        else
        {
        respond.json({ "message": "Successfully registered! Welcome to random savour!" });
        }
        });
    }


    
    loginUser(request, respond) 
    {
        var userid = request.body.userid;
        var password = request.body.password;

        var sql = "SELECT * FROM restaurant_reviews.user WHERE userid = ? AND password = ?;";

        db.query(sql, [userid, password], function (error, result) {
            if (error) {
                throw error;
            }
            else {
                if (result.length > 0) {
                    respond.json({ "message": "Success!" });
                }
                else {
                    respond.json({ "message": "Your UserId or Password is incorrect. Please try again!" });
                }
            }

        });
    }

    updateUser(request, respond) {

        var userObject = new User(request.params.id, request.params.userid, request.body.password, request.body.firstname,
            request.body.secondname, request.body.email, request.body.mobile);

        var sql = "UPDATE restaurant_reviews.user SET password = ?, firstname = ?, secondname = ?, email = ?, mobile = ?, WHERE userid = ?";

        var values = [userObject.getUserId(), userObject.getPassword(), userObject.getFirstName(), userObject.getSecondName(),
             userObject.getEmail(), userObject.getMobile()];
        db.query(sql, values, function (error, result) {
            if (error) {
                throw error;
            }
            else {
                respond.json(result);
            }
        });
    }

    deleteUser(request, respond)
     {
        var userID = request.params.id;
        var sql = "DELETE FROM restaurant_reviews.user WHERE _id = ?";
        db.query(sql, userID, function (error, result) {
            if (error) {
                throw error;
            }
            else {
                respond.json(result);
            }
        });
    }

    searchUser(request, respond)
    {
        var userid = request.params.userid;

       var sql = "SELECT * FROM restaurant_reviews.user WHERE userid = ?";

       db.query(sql, [userid], function (error, result) {
           if (error) {
               throw error;
           }
           else {
               respond.json(result);
           }
       });
   }
  
}        
module.exports = UsersDB;
