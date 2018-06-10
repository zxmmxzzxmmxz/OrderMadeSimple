load_order = function (){
	
	var $orders = $('#orders');
	$orders.html("");
	
	$.ajax({
		type: 'GET',
		url: '/rest/orders/allOrders',
		crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ sessionStorage.token
        },
		success: function(orders){
			$.each(orders, function(i, order){
				var anOrder = "";
				anOrder += "Order at time " + order.time + " has following:<br><ul>";
				$.each(order.orderDetails, function(i, orderPiece){
					anOrder += '<li>';
					anOrder += orderPiece.dish_name + " status: ";
					anOrder += orderPiece.status;
					anOrder += '</li>';
				});
				anOrder += '</ul>';
				$orders.append(anOrder);
			});
		}
	});
};

loadDishes = function(){
	$("#dish_pick").html("");
	$.ajax({
		type: 'GET',
		url: '/rest/dish/allDishesForRestaurantName/'+sessionStorage.restaurant_name,
		crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ sessionStorage.token
        },
		success: function(allDishes){
			dishes = allDishes;
			var selectList = "Pick Dish: <select class='dish'>";
			$.each(allDishes, function(i, eachDish){
				selectList += "<option value='"+eachDish.dish_id+"'>" + eachDish.dish_name + "</option>";
			});
			selectList += "</select><br>";
			$('#dish_pick').html(selectList);
		}
	});
};

create_dish_row = function() {
	var selectList = "Pick Dish: <select class='dish'>";
			$.each(dishes, function(i, eachDish){
				selectList += "<option value='"+eachDish.dish_id+"'>" + eachDish.dish_name + "</option>";
			});
			selectList += "</select><br>";
			$('#dish_pick').append(selectList);
};

$(load_order);

$(function (){
	$('#refresh_status').on('click',load_order);
    loadDishes();
    $('#restaurant_name').html(sessionStorage.restaurant_name);
	$('#add_more_dish').on('click',create_dish_row);
});

$(function(){
	$('#submit').on('click', function(){
		var selectedDishes = $('.dish');
		var restaurant = $('#restaurant').val();
		var orderDetails = [];
		for (var i = selectedDishes.length - 1; i >= 0; i--) {
			var orderDetail = {};
			orderDetail['dish_id'] = selectedDishes[i].value;
			orderDetails.push(orderDetail);
		}
		$.ajax({
			type: 'POST',
			url: '/rest/orders/add',
			dataType: 'json',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
                'Authorization':'cmpt470 '+ sessionStorage.token
			},
			data: JSON.stringify({
                           	"restaurant_id": restaurant,
                           	"orderDetails" : orderDetails
                       }),
			success: function(msg){
				alert("posted");
				load_order();
			},
			error: function(xhr, res, text){
				alert("error:" + text);
			}
		});
	});
});


/*$(function(){
	$.ajax({
		type: 'GET',
		url: '/rest/restaurant/allRestaurants',
		crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ sessionStorage.token
        },
		success: function(restaurants){
			var selectList = "Pick Restaurant: <select name='restaurants' id='restaurant'>";
			$.each(restaurants, function(i, restaurant){
				selectList += "<option value='"+restaurant.restaurant_id+"'>" + restaurant.restaurant_name + "</option>";
			});
			selectList += "</select>";
			$('#restaurant_pick').html(selectList);
			$('#restaurant').on('change',loadDishes);
			loadDishes();
		}
	});
});
*/