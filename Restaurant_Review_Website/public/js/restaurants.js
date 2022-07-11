
function getRestaurantData() {
    var request = new XMLHttpRequest();
    request.open('GET', restaurant_url, true);
    request.onload = function () {
        restaurant_array = JSON.parse(request.responseText);
        fetchComments();
        displayRestaurants(category);
    };
    request.send();
}

function displayRestaurants(category) {
    var table = document.getElementById("RestaurantTable");
    var restaurantCount = 0;
    var message = "";
    table.innerHTML = "";
    totalRestaurants = restaurant_array.length;
    for (var count = 0; count < totalRestaurants; count++) {
        //if (restaurant_array[count].availability == category) {
            var thumb = restaurant_array[count].thumb;
            var name = restaurant_array[count].name;
            var cell = '<div class="col-md-3" style="float: none; margin: 0 auto;">' +
                '<div class="flip-container" >' +
                '<div class="flipper">' +
                '<div class="front">' +
                '<a id="restaurants" href="#" data-toggle="modal" data-target="#restaurantModal" item=' + count + '>' +
                '<img src=' + thumb + ' />' +
                '</a>' +
                '</div>' +
                '<div class="back">' +
                '<div class="bg-dark mystyle text-center" >' +
                '<span><br>' + name + '</span><br>' +
                '<button href="#" data-toggle="modal" data-target="#restaurantModal" item="' + count + '" type="button" class="btn btn-sm" onClick="showRestaurantDetails(this)" >See More</button>' +
                '<button href="#" data-toggle="modal" data-target="#commentModal" item="' + count + '" type="button" class="btn btn-sm" onClick="showRestaurantComments(this)" >Comments</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            table.insertAdjacentHTML('beforeend', cell);
            restaurantCount++;
        }
    }
    message = restaurantCount + " " + category;
    document.getElementById("summary").textContent = message;
    document.getElementById("parent").textContent = "";
//}

function listRestaurants()
{
    category = "Restaurants";
    displayRestaurants(category);
    document.getElementById("nowMenu").classList.add("active");
    document.getElementById("comingMenu").classList.remove("active");
    document.getElementById("aboutMenu").classList.remove("active");
}

//This function is to display the individual restaurants details
//whenever the user clicks on "See More"
function showRestaurantDetails(element) {
    var item = element.getAttribute("item");
    currentIndex = item;
    document.getElementById("restaurantName").textContent = restaurant_array[item].restaurantname;
    document.getElementById("restaurantPictures").src = restaurant_array[item].pictures;
    document.getElementById("address").textContent = restaurant_array[item].address;
    document.getElementById("openinghours").textContent = restaurant_array[item].openinghours;
    document.getElementById("information").textContent = restaurant_array[item].information;
    document.getElementById("number").textContent = restaurant_array[item].number;
    }
    //This function opens a new window/tab and loads the
    //particular restaurant in the cinema website
    function linkWebsite() {
    window.open(restaurant_array[currentIndex].website, "_blank");
    }