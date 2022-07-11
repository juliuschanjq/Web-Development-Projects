"use strict"

const restaurantsdb = require('../models/RestaurantsDB');

var restaurantsDBObject = new restaurantsdb();

function routeRestaurants(app)
{
    app.route('/restaurants')
        .get(restaurantsDBObject.getAllRestaurants);
    
    app.route('/restaurants/:restaurantname')
        .get(restaurantsDBObject.searchRestaurantName);

}

module.exports = {routeRestaurants};