package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    @SerializedName("order_id")
    private int _order_id;
    @SerializedName("restaurant_id")
    private int _restaurant_id;
    @SerializedName("time")
    private String _time;
    @SerializedName("OrderDetails")
    private List<OrderDetail> _orderDetails;

    //so no one can create empty order
    private Order(){
    }

    public Order(int order_id, int restaurant_id, OffsetDateTime time) {
        _order_id = order_id;
        _restaurant_id = restaurant_id;
        _time = time.toString();
        _orderDetails = new ArrayList<>();
    }

    public Order(int order_id, int restaurant_id, OffsetDateTime time, List<OrderDetail> orderDetails) {
        _order_id = order_id;
        _restaurant_id = restaurant_id;
        _time = time.toString();
        _orderDetails = orderDetails;
    }

    public int getOrderId() {
        return _order_id;
    }

    public OffsetDateTime getTime() {
        return OffsetDateTime.parse(_time);
    }

    public void setOrderDetails(List<OrderDetail> orderDetails){
        _orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        _orderDetails.add(orderDetail);
    }
}
