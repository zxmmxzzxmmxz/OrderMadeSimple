package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName(value = "order_id")
    private int _order_id;

    @SerializedName(value = "dish_id")
    private int _dish_id;

    @SerializedName(value = "dish_name")
    private String _dish_name;

    @SerializedName(value = "status")
    private OrderDetailStatusTypeCode _status;

    private OrderDetail() {
    }

    public OrderDetail(int order_id, int dish_id,String dish_name, OrderDetailStatusTypeCode status){
        _order_id = order_id;
        _dish_id = dish_id;
        _status = status;
        _dish_name = dish_name;
    }

    public int getOrderId() {
        return _order_id;
    }

    public int getDishId() {
        return _dish_id;
    }

    public OrderDetailStatusTypeCode getStatus() {
        return _status;
    }
}
