// testJson = '[{"order_id":20,"restaurant_name":"joojak","time":"Fri, 6 Jul 2018 08:14:07 GMT","orderDetails":[{"order_detail_id":23,"order_id":20,"dish_ver_id":5,"status":"new"},{"order_detail_id":24,"order_id":20,"dish_ver_id":5,"status":"new"}]},' +
//     '{"order_id":21,"restaurant_name":"joojak","time":"Fri, 6 Jul 2018 08:14:07 GMT","orderDetails":[{"order_detail_id":25,"order_id":21,"dish_ver_id":5,"status":"done"},{"order_detail_id":26,"order_id":21,"dish_ver_id":5,"status":"new"}]},' +
//     '{"order_id":22,"restaurant_name":"joojak","time":"Fri, 6 Jul 2018 08:14:07 GMT","orderDetails":[{"order_detail_id":31,"order_id":22,"dish_ver_id":5,"status":"done"},{"order_detail_id":32,"order_id":22,"dish_ver_id":5,"status":"new"}]},' +
//     '{"order_id":23,"restaurant_name":"joojak","time":"Fri, 6 Jul 2018 08:14:07 GMT","orderDetails":[{"order_detail_id":53,"order_id":23,"dish_ver_id":5,"status":"done"},{"order_detail_id":54,"order_id":23,"dish_ver_id":5,"status":"new"}]},' +
//     '{"order_id":24,"restaurant_name":"joojak","time":"Fri, 6 Jul 2018 08:14:07 GMT","orderDetails":[{"order_detail_id":77,"order_id":24,"dish_ver_id":5,"status":"done"},{"order_detail_id":78,"order_id":24,"dish_ver_id":5,"status":"new"}]}]';
// test_order = new Order(JSON.parse(testJson)[0]);
//test_orders = [new Order(JSON.parse(testJson)[0]), new Order(JSON.parse(testJson)[1]), new Order(JSON.parse(testJson)[2]), new Order(JSON.parse(testJson)[3]), new Order(JSON.parse(testJson)[4])]

addOrders = function(orders){
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

$(function () {
    something(sessionStorage.restaurant_name, sessionStorage.token, addOrders, function(msg){
        sessionStorage.message = msg;
    });

});