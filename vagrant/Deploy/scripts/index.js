var timer;

addOrders = function(orders){

    $("#order-list").empty();
    console.log(orders);

    let container = $("#order-list");
    let flow = new ContainerFlow(5, "order-new-", container, 2);
    let setOfDishesVerIDs = new Set();
    for (let order of orders){
        for (let detail of order.orderDetails){
            $("#order-id-" + detail.order_id).append(createDetailItem(detail));
            setOfDishesVerIDs.add(detail.dish_ver_id);
        }
    }

    getDishes(Array.from(setOfDishesVerIDs), sessionStorage.token, function(foundDishes){
        console.log(foundDishes);
        for (let order of orders){
            flow.insert(getOrderList(order, foundDishes));
        }
        flow.finish();
    });

};

loadOrders = function(){
    loadAllOpenOrdersForRestaurant(sessionStorage.restaurant_name, sessionStorage.token, addOrders, function(msg){
        if(msg.indexOf("FATAL") >= 0){
            console.log("loadOrder done msg:" + msg);
            $('#errormsg').html("<div class='alert alert-danger' role='alert'>"+msg+"</div>");
            clearTimeout(timer);
            $('#refresh_status').html('');
        }
        sessionStorage.message = msg;
    });
};

$(function () {

    //$('#refresh_status').on('click',loadOrders);

    $('.carousel').carousel();

    var timerCount = 10;
    timer = setInterval(function() {
        $('#refresh_status').html('refresh in '+timerCount+'s');
        timerCount--;
        if(timerCount==0){
            // reload;
            timerCount=10;
            $('#refresh_status').html('refreshing...');
            loadOrders();
        }
        console.log(2);
    }, 1000);

    loadOrders();
});