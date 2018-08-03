addOrders = function(orders){
    $("#order-list").empty();
    let colIndex = 0;
    let rowIndex = 0;
    for (let order of orders){
        if(colIndex === 0){
            rowIndex += 1;
            $("#order-list").append(createOrderListRow(rowIndex));
        }
        $("#order-list-row-"+rowIndex).append(createOrderListCol(order.order_id));
        $('#order-list-col-'+order.order_id).append(createOrderList(order));
        let details = order.orderDetails;

        for (let detail of details) {
            $("#order-id-" + detail.order_id).append(createDetailItem(detail));
            getDish(
                detail.dish_ver_id,
                sessionStorage.token,
                function (dish) {
                    let detailelement = $("#detail-id-"+detail.order_detail_id);
                    detailelement.append(createDishName(dish));
                    detailelement.append(createDishDescription(dish));
                }, function (dish) {
                    let detailelement = $("#detail-id-"+detail.order_detail_id);
                    detailelement.append(createDishName(dish));
                    detailelement.append(createDishDescription(dish));
                });
        }
        colIndex += 1;
        if(colIndex === 4){
            colIndex = 0;
        }
    }

    while(colIndex !== 0){
        let el = document.createElement("div");
        el.classList.add('col');
        $("#order-list-row-"+rowIndex).append(el);
        colIndex += 1;
        if(colIndex === 4){
            colIndex = 0;
        }
    }
};

loadOrders = function(){
    something(sessionStorage.restaurant_name, sessionStorage.token, addOrders, function(msg){
        sessionStorage.message = msg;
    });
};

$(function () {
    $('#refresh_status').on('click',loadOrders);
    loadOrders();
});