"use strict"

const UserDB = require('../models/UsersDB');

var usersDBObject = new UserDB();

function routeUsers(app)
{
    app.route('/users')
        .get(usersDBObject.getAllUsers);

    app.route('/register')    
        .post(usersDBObject.registerUser);

    app.route('/login')
        .post(usersDBObject.loginUser);

    app.route('/users/:id')
        .delete(usersDBObject.deleteUser);

    app.route('/users/:userid')
        .get(usersDBObject.searchUser);    
}

module.exports = {routeUsers};