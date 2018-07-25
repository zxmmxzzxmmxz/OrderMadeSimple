mock_order = new Order(JSON.parse('{"order_id":1,"restaurant_name":"joojak","time":"Tue, 19 Oct 2004 17:23:54 GMT","orderDetails":[{"order_detail_id":1,"order_id":1,"dish_ver_id":1,"status":"new"},{"order_detail_id":1,"order_id":1,"dish_ver_id":1,"status":"new"}],"order_status":"new","table_number":"3","included_in_eod_report":true,"created_by_user":1}'));
mock_dish_list = [new Dish(JSON.parse('{"dish_id":1,"dish_ver_id":1,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}'))]

loadOrder = function(order){
	let container = $("#orderDetailContainer");
	container.empty();
	let flow = new ContainerFlow(1,'order-detail-',container,12);
	let dish_ver_ids = order.orderDetails.map(detail => detail.dish_ver_id);
	flow.insert(createOrderMetaDataPanel(order));
	flow.finish();
	//get all dishes
	let dishes = mock_dish_list;
	let orderList = getOrderList(order, dishes);	
	flow.insert(orderList);
	flow.finish();
	flow.insert(createVerticalButtonBar("actionButtons"));
	let actionList = [{buttonText:"Complete Order", buttonType:"primary", clickHandler:completeOrderHandler},{buttonText:"Delete Order", buttonType:"danger"},{buttonText:"Discount", buttonType:"primary"}];
	loadActionButtons(actionList);	
	flow.finish();
}

loadActionButtons = function(actionList){
	let container =  $("#actionButtons");
	for (let action of actionList){
		container.append(createButton(action.buttonText, action.buttonType, action.clickHandler));
	}
}

updateOrder = function(order){
	//call orderAPI to update this order
	alert("Order Updated!");
	loadOrder(mock_order);
}

completeOrderHandler = function(){
	let order = new Order(JSON.parse(sessionStorage.currentOrder));
	for (let detail of order.orderDetails){
		detail.status = "done";
	}
	order.status = "done";
	mock_order = order;
	updateOrder(order);
}

$(function(){
	sessionStorage.currentOrder = mock_order.toJson();
	loadOrder(mock_order);
});	