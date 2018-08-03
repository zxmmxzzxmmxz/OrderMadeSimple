
function createOrderReportItem(order)
{
    var item = '<tr>';
    item += '<td>'+order.order_id+'</td>';
    item += '<td>'+order.order_status+'</td>';
    item += '<td>'+order.restaurant_name+'<br/>Table: '+order.table_number+'<br/>Time: '+order.time+'</td>';
    //item += '<td>'+order.time+'</td>';
    item += '<td class="res_info"></td>';
    item += '<td id="od_info_'+order.order_id+'"></td>';
    item+='</tr>';
    return item;
}

getReportOrderList = function(order, dishes){
    var htmlAll = "";
    for (let detail of order.orderDetails){
        let dish = dishes.filter(dish => dish.dish_ver_id === detail.dish_ver_id)[0];
        if(dish)
        {
            //detailEl.appendChild(createDishName(foundDish));
            //detailEl.appendChild(createDishDescription(foundDish));
            var item = "<div class=\"card\" style=\"width: 18rem;\">\n" +
                "  <div class=\"card-body\">\n" +
                "    <h5 class=\"card-title\">"+dish.dish_name+"</h5>\n" +
                "    <h6 class=\"card-title\">"+dish.menu_flag+"</h6>\n" +
                "    <p class=\"card-text\">"+dish.description+"</p>\n" +
                "    <h6 class=\"card-title text-right\">$"+dish.price+"</h6>\n" +
                "  </div>\n" +
                "</div>";
            htmlAll += item;
        }
    }
    return htmlAll;
};

function loopOrders(orders)
{
    console.log(orders);
    var report_container = $('#report_container tbody');

    loadRestaurant(sessionStorage.restaurant_id, sessionStorage.token, function (item) {
        var html = createHourAndContactList(item);
        $('.res_info').html(html);
        console.log(item);
    }, function (e) {
        console.log(e)
    });


    for (let order of orders){
        var item = createOrderReportItem(order);
        report_container.append(item);
        let setOfDishesVerIDs = new Set();
        for (let detail of order.orderDetails){
            setOfDishesVerIDs.add(detail.dish_ver_id);
        }
        getDishes(Array.from(setOfDishesVerIDs), sessionStorage.token, function(foundDishes){
            console.log(foundDishes);
            $('#od_info_'+order.order_id).html(getReportOrderList(order, foundDishes));
        });
    }
}

$(function () {
    //http://localhost:8080/rest/restaurant/info/2
    console.log(sessionStorage);

    loadAllOrdersForRestaurant(sessionStorage.restaurant_name, sessionStorage.token, loopOrders, function(msg){
        if(msg.indexOf("FATAL") >= 0){
            console.log("loadOrder done msg:" + msg);
            $('#errormsg').html("<div class='alert alert-danger' role='alert'>"+msg+"</div>");
            clearTimeout(timer);
            $('#refresh_status').html('');
        }
        sessionStorage.message = msg;
    });

});
