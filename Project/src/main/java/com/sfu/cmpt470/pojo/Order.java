package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    @SerializedName("order_id")
    private long _order_id;
    @SerializedName("restaurant_id")
    private long _restaurant_id;
    @SerializedName("time")
    private String _time;
    @SerializedName("orderDetails")
    private List<OrderDetail> _orderDetails;

    //so no one can create empty order
    private Order(){
    }

    public Order(int order_id, int restaurant_id, OffsetDateTime time) {
        _order_id = order_id;
        _restaurant_id = restaurant_id;
        _time = time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        _orderDetails = new ArrayList<>();
    }

    public Order(int order_id, int restaurant_id, OffsetDateTime time, List<OrderDetail> orderDetails) {
        _order_id = order_id;
        _restaurant_id = restaurant_id;
        _time = time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        _orderDetails = orderDetails;
    }

    public void setTime(OffsetDateTime _time) {
        this._time = _time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public long getOrderId() {
        return _order_id;
    }

    public long getRestaurantId() {
        return _restaurant_id;
    }

    public OffsetDateTime getTime() {
        return OffsetDateTime.parse(_time,DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public void setOrderDetails(List<OrderDetail> orderDetails){
        _orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        _orderDetails.add(orderDetail);
    }

    public List<OrderDetail> getOrderDetails(){
        return new ArrayList<>(_orderDetails);
    }

    public void setOrderID(long orderId) {
        _order_id = orderId;
        for (OrderDetail orderDetail : _orderDetails) {
            orderDetail.setOrderID(orderId);
        }

    }
}
