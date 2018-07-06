package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    @SerializedName("order_id")
    private long _order_id;
    @SerializedName("restaurant_name")
    private String _restaurantName;
    @SerializedName("time")
    private String _time;
    @SerializedName("orderDetails")
    private List<OrderDetail> _orderDetails;
    @SerializedName("order_status")
    private String _orderStatus;

    public Order(){
        _orderDetails = new ArrayList<>();
        _orderStatus = "new";
    }

    public Order(int order_id, String restaurantName, OffsetDateTime time, String orderStatus) {
        _orderStatus = orderStatus;
        _order_id = order_id;
        _restaurantName = restaurantName;
        _time = time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        _orderDetails = new ArrayList<>();
    }

    public Order(int order_id, String restaurantName, OffsetDateTime time, List<OrderDetail> orderDetails) {
        _order_id = order_id;
        _restaurantName = restaurantName;
        _time = time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        _orderDetails = orderDetails;
    }

    public void setTime(OffsetDateTime _time) {
        this._time = _time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public void setRestaurantName(String _restaurantName) {
        this._restaurantName = _restaurantName;
    }

    public long getOrderId() {
        return _order_id;
    }

    public String getRestaurantName() {
        return _restaurantName;
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

    public String getOrderStatus() {
        return _orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this._orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return _order_id == order._order_id &&
                Objects.equals(_restaurantName, order._restaurantName) &&
                Objects.equals(_time, order._time) &&
                Objects.equals(_orderDetails, order._orderDetails) &&
                Objects.equals(_orderStatus, order._orderStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(_order_id, _restaurantName, _time, _orderDetails, _orderStatus);
    }
}
