"use strict";

class restaurant
{
constructor(id, name, thumb, pictures, information, address, openinghours, number, website)
    {
    this.id = id;
    this.restaurantname = name;
    this.thumb = thumb;
    this.pictures = pictures;
    this.information = information;
    this.address = address;
    this.openinghours = openinghours;
    this.number = number;
    this.website = website;

    }

    getId() 
    {
        return this.id
    }

    getName()
    {
        return this.name
    }

    getThumb()
    {
        return this.thumb
    }

    getPictures()
    {
        return this.Pictures
    }

    getInformation()
    {
        return this.information
    }


    getAddress()
    {
        return this.address
    }

    getOpeningHours()
    {
        return this.openinghours
    }

    getNumber()
    {
        return this.number
    }

    getWebsite()
    {
        return this.website
    }

}    

module.exports = restaurant;