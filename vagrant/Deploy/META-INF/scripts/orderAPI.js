OrderDetail = class {
    constructor(fromJson){
        this.order_detail_id = fromJson.order_detail_id;
        this.order_id = fromJson.order_id;
        this.dish_ver_id = fromJson.dish_ver_id;
        this.status = fromJson.status;
    }

    toJson(){
        return JSON.stringify({
            "order_detail_id":this.order_detail_id,
            "order_id":this.order_id,
            "dish_ver_id":this.dish_ver_id,
            "status":this.status
        });
    }

    toliterals(){
        return {
            "order_detail_id":this.order_detail_id,
            "order_id":this.order_id,
            "dish_ver_id":this.dish_ver_id,
            "status":this.status
        }
    }
};

Order = class {
    constructor(fromJson) {
        this.order_id = fromJson.order_id;
        this.order_status = fromJson.order_status;
        this.restaurant_name = fromJson.restaurant_name;
        this.time = fromJson.time;
        this.orderDetails = [];
        for (let orderDetail of fromJson.orderDetails) {
            this.orderDetails.push(new OrderDetail(orderDetail))
        }
    }

    toJson(){
        return JSON.stringify({
            "order_id":this.order_id,
            "order_status":this.order_status,
            "restaurant_name":this.restaurant_name,
            "time":this.time,
            "orderDetails":orderDetails.map(o => o.toliterals())
        })
    }
};

something = function(restaurant_name, token, successCallBack, errorCallBack){
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
            errorCallBack(jqXHR.responseText)
        }
    })
};
