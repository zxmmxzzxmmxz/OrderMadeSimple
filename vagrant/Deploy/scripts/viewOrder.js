newAddOrders = function(orders){
    let container = $("#order-list");
    container.empty();
    let flow = new ContainerFlow(5, "order-new-",container, 2);
    let setOfDishesVerIDs = new Set();
    for (let order of orders){
        for (let detail of order.orderDetails){
            setOfDishesVerIDs.add(detail.dish_ver_id);
        }
    }

    getDishes(Array.from(setOfDishesVerIDs), sessionStorage.token, function(foundDishes){
        for (let order of orders){
            flow.insert(getOrderList(order, foundDishes, sessionStorage.token, function (msg) {
                loadOrdersInPage();
            },function (msg) {

            }));
        }
        flow.finish();
    });
};

loadOrdersInPage = function(){
    loadAllOpenOrdersForRestaurant(sessionStorage.restaurant_name, sessionStorage.token, newAddOrders, function(msg){
        sessionStorage.message = msg;
    });
};

$(function () {
    loadOrdersInPage();
});