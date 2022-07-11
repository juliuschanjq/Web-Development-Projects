"use strict"

var db = require('../db-connection');
const Comment = require('../models/comment');

class commentsDB
{
    getAllComments(request, respond)
    {
        var sql = "SELECT * FROM restaurant_reviews.comment";
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

    addComment(request, respond) {
        var now = new Date();
        var commentObject = new Comment(null, request.body.restaurantId, request.body.restaurant, request.body.review,
            request.body.username, request.body.rating, now.toString());

        var sql = "INSERT INTO restaurant_reviews.comment (restaurantId, restaurant, review, username, rating, datePosted) VALUES(?, ?, ?, ?, ?, ?)";


        var values = [commentObject.getRestaurantId(), commentObject.getRestaurant(), commentObject.getReview(),
        commentObject.getUsername(), commentObject.getRating(), commentObject.getDatePosted()];


        db.query(sql, values, function (error, result) {
            if (error) {
                throw error;
            }
            else {
                respond.json(result);
            }
        });
    }   

    updateComment(request, respond) {
        var now = new Date();

        var commentObject = new Comment(request.params.id, request.body.restaurantId, request.body.restaurant,
            request.body.review, request.body.username, request.body.rating, now.toString());

        var sql = "UPDATE restaurant_reviews.comment SET Username = ?, review = ?, rating = ?, datePosted=? WHERE _id = ?";

        var values = [commentObject.getUsername(), commentObject.getReview(), commentObject.getRating(), commentObject.getDatePosted(), commentObject.getId()];
        db.query(sql, values, function (error, result) {
            if (error) {
                throw error;
            }
            else {
                respond.json(result);
            }
        });
    }
        
        

    deleteComment(request, respond) {
        var commentID = request.params.id;

        var sql = "DELETE FROM restaurant_reviews.comment WHERE _id = ?";

        db.query(sql, commentID, function (error, result) {
            if (error) {
                throw error;
            }
            else {
                respond.json(result);
            }
        });
    }
        
  
}        
module.exports = commentsDB;