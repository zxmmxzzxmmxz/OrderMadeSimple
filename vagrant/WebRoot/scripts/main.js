$(function (){
	
	var $orders = $('#orders');
	
	$.ajax({
		type: 'GET',
		url: '/project/rest/orders/allOrders',
		success: function(orders){
			$.each(orders, function(i, order){
				var anOrder = "";
				anOrder += "Order at time " + order.time + " has following:<br><ul>";
				$.each(order.OrderDetails, function(i, orderPiece){
					anOrder += '<li>';
					anOrder += orderPiece.dish_name + " status: ";
					anOrder += orderPiece.status;
					anOrder += '</li>';
				});
				anOrder += '</ul>'
				$orders.append(anOrder);
			});
		}
	});

	$("#submit").on('click', function(){
		$.ajax({
                url: '/project/rest/orders/addOrder', 
                type : "POST", 
                dataType : 'json', 
                data : $("#form").serialize(), 
                success : function(result) {
                    // you can see the result from the console
                    // tab of the developer tools
                    console.log(result);
                },
                error: function(xhr, resp, text) {
                    console.log(xhr, resp, text);
                }
            })
	});
})