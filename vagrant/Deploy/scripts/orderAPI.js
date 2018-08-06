//mock_order = '{"order_id":1,"restaurant_name":"joojak","time":"Tue, 19 Oct 2004 17:23:54 GMT","orderDetails":[{"order_detail_id":1,"order_id":1,"dish_ver_id":1,"status":"new"},{"order_detail_id":3,"order_id":1,"dish_ver_id":4,"status":"new"},{"order_detail_id":4,"order_id":1,"dish_ver_id":3,"status":"new"}],"order_status":"new","table_number":"3","included_in_eod_report":true,"created_by_user":1}'

OrderDetail = class {
    constructor(fromJson){
        if(fromJson !== undefined){
            this.order_detail_id = fromJson.order_detail_id;
            this.order_id = fromJson.order_id;
            this.dish_ver_id = fromJson.dish_ver_id;
            this.status = fromJson.status;
            this.special_note = fromJson.special_note;
            this.tag = fromJson.tag;
        }
    }

    toJson(){
        return JSON.stringify({
            "order_detail_id":this.order_detail_id,
            "order_id":this.order_id,
            "dish_ver_id":this.dish_ver_id,
            "status":this.status,
            "special_note":this.special_note,
            "tag":this.tag
        });
    }

    toliterals(){
        return {
            "order_detail_id":this.order_detail_id,
            "order_id":this.order_id,
            "dish_ver_id":this.dish_ver_id,
            "status":this.status,
            "special_note":this.special_note,
            "tag":this.tag
        }
    }
};

Order = class {
    constructor(fromJson) {
        this.orderDetails = [];
        if(fromJson !== undefined) {
            this.order_id = fromJson.order_id;
            this.order_status = fromJson.order_status;
            this.restaurant_name = fromJson.restaurant_name;
            this.time = fromJson.time;
            this.table_number = fromJson.table_number;
            this.included_in_eod_report = fromJson.included_in_eod_report;
            this.created_by_user = fromJson.created_by_user;
            for (let orderDetail of fromJson.orderDetails) {
                this.orderDetails.push(new OrderDetail(orderDetail))
            }
        }
    }

    toJson(){
        return JSON.stringify({
            "order_id":this.order_id,
            "order_status":this.order_status,
            "restaurant_name":this.restaurant_name,
            "time":this.time,
            "orderDetails":this.orderDetails.map(o => o.toliterals()),
            "table_number":this.table_number,
            "included_in_eod_report":this.included_in_eod_report,
            "created_by_user":this.created_by_user
        })
    }
};

loadAllOpenOrdersForRestaurant = function(restaurant_name, token, successCallBack, errorCallBack){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/orders/allOpenOrders/'+restaurant_name,
        crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        success: function(orders){
            let result = [];
            $.each(orders, function(i, order){
                result.push(new Order(order));
            });
            successCallBack(result);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText)
        }
    })
};

loadAllOrdersForRestaurant = function(restaurant_name, token, successCallBack, errorCallBack){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/orders/allOrders/'+restaurant_name,
        crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        success: function(orders){
            let result = [];
            $.each(orders, function(i, order){
                result.push(new Order(order));
            });
            successCallBack(result);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText)
        }
    })
};

getOrderByOrderID = function(orderID, token, successCallBack, errorCallBack){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/orders/getOrder/'+orderID,
        crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        success: function(order){
            successCallBack(new Order(order));
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText);
        }
    })
};

submitOrder = function(order, orderToken, successCallBack, errorCallBack){
    $.ajax({
        type: 'POST',
        url: NEED_PROJECT+'/rest/orders/add',
        dataType: 'text',
        headers: {
            'Content-Type': 'application/json',
            'Authorization':'cmpt470 '+ orderToken
        },
        data: order.toJson(),
        success: function(msg){
            successCallBack(msg);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText);
        }
    });
};

updateOrderDetail = function(orderDetail,token,successCallBack, errorCallBack){
    $.ajax({
        type: 'POST',
        url: NEED_PROJECT+'/rest/orderDetail/updateOrderDetail',
        dataType: 'text',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization':'cmpt470 '+ token
        },
        data: orderDetail.toJson(),
        success: function(msg){
            successCallBack(msg);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText);
        }
    });
};
