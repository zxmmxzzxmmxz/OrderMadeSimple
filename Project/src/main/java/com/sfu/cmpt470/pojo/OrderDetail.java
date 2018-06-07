package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName(value="order_detail_id")
    private long _orderDetailID;

    @SerializedName(value = "order_id")
    private long _orderID;

    @SerializedName(value = "dish_id")
    private long _dishID;

    @SerializedName(value = "dish_name")
    private String _dishName;

    @SerializedName(value = "status")
    private OrderDetailStatusTypeCode _status;

    //it must have something
    private OrderDetail() {
    }

    public OrderDetail(long orderDetailID,long order_id, long dish_id, String dish_name, OrderDetailStatusTypeCode status){
        _orderDetailID = orderDetailID;
        _orderID = order_id;
        _dishID = dish_id;
        _status = status;
        _dishName = dish_name;
    }

    public void setOrderDetailID(long id){
        _orderDetailID = id;
    }

    public long getOrderDetailID(){
        return _orderDetailID;
    }

    public long getOrderId() {
        return _orderID;
    }

    public long getDishId() {
        return _dishID;
    }

    public OrderDetailStatusTypeCode getStatus() {
        return _status;
    }

    public void setStatus(OrderDetailStatusTypeCode status){
        _status = status;
    }

    public void setOrderID(long orderId) {
        _orderID = orderId;
    }
}
