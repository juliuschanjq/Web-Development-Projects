"use strict"
const { request } = require('express');
var db = require('../db-connection');

class RestaurantsDB
{
    getAllRestaurants(request, respond)
    {
        var sql = "SELECT * FROM restaurant_reviews.restaurant";
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

        searchRestaurantName(request, respond)
    {
        var restaurantname = request.params.restaurantname;

       var sql = "SELECT * FROM restaurant_reviews.restaurant WHERE restaurantname = ?";

       db.query(sql, [restaurantname], function (error, result) {
           if (error) {
               throw error;
           }
           else {
               respond.json(result);
           }
       });
   }
       

}    
module.exports = RestaurantsDB;
