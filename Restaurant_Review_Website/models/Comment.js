"use strict";

class Comment 
{
    constructor(id, restaurantId, restaurant, review, username, rating, datePosted)
    {
        this.id = id;
        this.restaurantId = restaurantId;
        this.restaurant = restaurant;
        this.review = review;
        this.username = username;
        this.rating = rating;
        this.datePosted = datePosted;
    }

    getId() 
    {
        return this.id;
    }
        

    setRestaurantId(restaurantId) 
    {
        this.restaurantId = restaurantId;
    }
  
    getRestaurantId() 
    {
        return this.restaurantId;
    }

    setRestaurant(restaurant) 
    {
        this.restaurant = restaurant;
    }
    
    getRestaurant() 
    {
        return this.restaurant;
    }

    setReview(review) 
    {
        this.review = review;
    }

    getReview() 
    {
        return this.review;
    }

    setUsername(username) 
    {
        this.username = username;
    }

    getUsername() 
    {
        return this.username;
    }

    setRating(rating) 
    {
        this.rating = rating;
    }

    getRating() 
    {
        return this.rating;
    }

    setDatePosted(datePosted)
    {
        this.datePosted = datePosted;
    }    

    getDatePosted() 
    {
        return this.datePosted;
    }    

   
        
}

module.exports = Comment;