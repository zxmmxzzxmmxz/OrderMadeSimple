load_order = function (){
	
	var $orders = $('#orders');
	$orders.html("");
	
	$.ajax({
		type: 'GET',
		url: '/rest/orders/allOrders/'+sessionStorage.restaurant_name,
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
                    anOrder += '<button value ="'+orderPiece.order_detail_id+'" class="update_order_detail_class" data-order-id="'+orderPiece.order_id+'">Mark as done</button><br>';
					anOrder += '</li>';
				});
				anOrder += '</ul>';
				anOrder += '<button value ="'+order.order_id+'" class="update_order_class">Mark as done</button><br>';
				$orders.append(anOrder);
				$(".update_order_class[value='"+order.order_id+"']").on('click',function () {
                    $.ajax({
                        type: 'POST',
                        url: '/rest/orders/updateOrder',
                        dataType: 'text',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            'Authorization':'cmpt470 '+ sessionStorage.token
                        },
                        data: JSON.stringify({
                            "order_id": $(this).val(),
                            "order_status" : "done"
                        }),
                        success: function(msg){
                            load_order();
                        },
                        error:function (jqXHR, textStatus, errorThrown) {
                            //window.location.href = "login.html";
                        }
                    });
                });

                $.each(order.orderDetails, function(i, orderPiece){
                    $(".update_order_detail_class[value='"+orderPiece.order_detail_id+"']").on('click',function () {
                        $.ajax({
                            type: 'POST',
                            url: '/rest/orderDetail/updateOrderDetail',
                            dataType: 'text',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json',
                                'Authorization':'cmpt470 '+ sessionStorage.token
                            },
                            data: JSON.stringify({
                                "order_detail_id":$(this).val(),
                                "order_id":$(this).attr("data-order-id"),
                                "status" : "done"
                            }),
                            success: function(msg){
                                load_order();
                            },
                            error:function (jqXHR, textStatus, errorThrown) {
                                //window.location.href = "login.html";
                            }
                        });
                    });
                });
			});
		},
		error:function (jqXHR, textStatus, errorThrown) {
            //window.location.href = "login.html";
        }
	});
};

createDishes = function(){
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
				selectList += "<option value='"+eachDish.dish_ver_id+"'>" + eachDish.dish_name + "</option>";
			});
			selectList += "</select><br>";
			$('#dish_pick').html(selectList);
		},
        error:function (jqXHR, textStatus, errorThrown) {
            //window.location.href = "login.html";
        }
	});
};

loadDishesCube = function(){
	$("#dish_panel").html("");
	$.ajax({
		type: 'GET',
		url: '/rest/dish/allDishesForRestaurantName/'+sessionStorage.restaurant_name,
		crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ sessionStorage.token
        },
		success: function(allDishes){
			dishes = allDishes;
			var selectList = "Pick Dish:";
			$.each(allDishes, function(i, eachDish){
				selectList += "<input name ='" + eachDish.dish_ver_id+ "' type = 'button' class='dish_cube' value='" + eachDish.dish_name +"'>";
			});
			$('#dish_panel').html(selectList);
			
			var orderList = "";
			var dish_name_temp = "";
			var dish_id_temp = "";

			$(".dish_cube").click(function(){
				$(".modal").show();
				dish_name_temp = $(this).attr('value');
				dish_id_temp = $(this).attr('name');
			});

			$("#comfirm_dish").click(function(){
				if ($('#order_list').is(':empty')){
					orderList = "";
				}

				var number_of_order = $('#number_of_order').val();
				for(i = 0; i < number_of_order; i++) { 
					orderList += '<li class="dish" value="'+dish_id_temp+'">'+ dish_name_temp +"</li>";
				}

				$("#order_list").html(orderList);
				$(".modal").hide();
				dish_name_temp = "";
			});

			$("#close").click(function(){
				$(".modal").hide();
				dish_name_temp = "";
			});
		},
        error:function (jqXHR, textStatus, errorThrown) {
            //window.location.href = "login.html";
        }
	});
};




create_dish_row = function() {
	var selectList = "Pick Dish: <select class='dish'>";
			$.each(dishes, function(i, eachDish){
				selectList += "<option value='"+eachDish.dish_ver_id+"'>" + eachDish.dish_name + "</option>";
			});
			selectList += "</select><br>";
			$('#dish_pick').append(selectList);
};

$(load_order);

$(function (){
	$('#refresh_status').on('click',load_order);
    createDishes();
    $('#restaurant_name').html(sessionStorage.restaurant_name);
	$('#add_more_dish').on('click',create_dish_row);
	loadDishesCube();
    let orders = getAllActiveOrders(sessionStorage.restaurant_name, sessionStorage.token, function(msg){alert(msg)});
});

$(function(){
	$('#submit').on('click', function(){
		var selectedDishes = $('.dish');
		var orderDetails = [];
		for (var i = selectedDishes.length - 1; i >= 0; i--) {
			var orderDetail = {};
			orderDetail['dish_ver_id'] = selectedDishes[i].value;
			orderDetails.push(orderDetail);
		}
		$.ajax({
			type: 'POST',
			url: '/rest/orders/add',
			dataType: 'text',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
                'Authorization':'cmpt470 '+ sessionStorage.token
			},
			data: JSON.stringify({
                           	"restaurant_name": sessionStorage.restaurant_name,
                           	"orderDetails" : orderDetails
                       }),
			success: function(msg){
				load_order();
				$('#order_list').empty();
			},
            error:function (jqXHR, textStatus, errorThrown) {
                window.location.href = "login.html";
            }
		});
	});
});

$(function(){

	$('#clear_order_list').on('click', function(){
		$('#order_list').empty();
	});

});

