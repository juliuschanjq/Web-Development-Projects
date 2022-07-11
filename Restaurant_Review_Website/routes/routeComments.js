"use strict"

const reviewsDB = require('../models/CommentsDB');

var reviewsDBObject = new reviewsDB();

function routeComments(app)
{
    app.route('/comments')
        .post(reviewsDBObject.addComment)
        .get(reviewsDBObject.getAllComments);

    app.route('/comments/:id')
        .put(reviewsDBObject.updateComment)
        .delete(reviewsDBObject.deleteComment);  
        
}
module.exports = {routeComments};