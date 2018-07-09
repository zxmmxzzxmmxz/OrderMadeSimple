package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OrderDetail {
    @SerializedName(value="order_detail_id")
    private long _orderDetailID;

    @SerializedName(value = "order_id")
    private long _orderID;

    @SerializedName(value = "dish_ver_id")
    private long _dishVerID;

    @SerializedName(value = "status")
    private OrderDetailStatusTypeCode _status;

    //it must have something
    private OrderDetail() {
    }

    private OrderDetail(long orderDetailID,long order_id, long dishVerId, OrderDetailStatusTypeCode status){
        _orderDetailID = orderDetailID;
        _orderID = order_id;
        _dishVerID = dishVerId;
        _status = status;
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

    public long getDishVerId() {
        return _dishVerID;
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

    public static OrderDetailBuilder newBuilder(){
        return new OrderDetailBuilder();
    }

    public OrderDetailBuilder toBuilder(){
        OrderDetailBuilder builder = newBuilder();
        return builder.setOrderID(_orderID)
                .setOrderDetailID(_orderDetailID)
                .setStatus(_status)
                .setDishVerID(_dishVerID);
    }

    public static class OrderDetailBuilder{
        private long _orderDetailID;
        private long _orderID;
        private long _dishID;
        private OrderDetailStatusTypeCode _status;

        public OrderDetailBuilder(){
            _orderDetailID = -1;
            _orderID = -1;
            _dishID = -1;
            _status = OrderDetailStatusTypeCode.NEW;
        }

        public OrderDetailBuilder setOrderDetailID(long detailID){
            _orderDetailID = detailID;
            return this;
        }

        public OrderDetailBuilder setOrderID(long orderID){
            _orderID = orderID;
            return this;
        }

        public OrderDetailBuilder setDishVerID(long dishVerID){
            _dishID = dishVerID;
            return this;
        }

        public OrderDetailBuilder setStatus(OrderDetailStatusTypeCode status){
            _status = status;
            return this;
        }

        public OrderDetail build(){
            return new OrderDetail(_orderDetailID, _orderID, _dishID, _status);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return _orderDetailID == that._orderDetailID &&
                _orderID == that._orderID &&
                _dishVerID == that._dishVerID &&
                _status == that._status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(_orderDetailID, _orderID, _dishVerID, _status);
    }
}
