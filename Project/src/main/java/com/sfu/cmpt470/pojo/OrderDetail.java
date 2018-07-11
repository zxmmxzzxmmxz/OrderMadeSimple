package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OrderDetail {
    @SerializedName(value="order_detail_id")
    private Long _orderDetailID;

    @SerializedName(value = "order_id")
    private Long _orderID;

    @SerializedName(value = "dish_ver_id")
    private Long _dishVerID;

    @SerializedName(value = "status")
    private OrderDetailStatusTypeCode _status;

    @SerializedName(value = "special_note")
    private String _specialNote;

    //it must have something
    private OrderDetail() {
    }

    private OrderDetail(Long orderDetailID,Long order_id, Long dishVerId, OrderDetailStatusTypeCode status, String specialNote){
        _orderDetailID = orderDetailID;
        _orderID = order_id;
        _dishVerID = dishVerId;
        _status = status;
        _specialNote = specialNote;
    }

    public String getSpecialNote(){
        return _specialNote;
    }

    public Long getOrderDetailID(){
        return _orderDetailID;
    }

    public Long getOrderId() {
        return _orderID;
    }

    public Long getDishVerId() {
        return _dishVerID;
    }

    public OrderDetailStatusTypeCode getStatus() {
        return _status;
    }

    public void setStatus(OrderDetailStatusTypeCode status){
        _status = status;
    }

    public static OrderDetailBuilder newBuilder(){
        return new OrderDetailBuilder();
    }

    public OrderDetailBuilder toBuilder(){
        OrderDetailBuilder builder = newBuilder();
        return builder.setOrderID(_orderID)
                .setOrderDetailID(_orderDetailID)
                .setStatus(_status)
                .setDishVerID(_dishVerID)
                .setSpecialNote(_specialNote);
    }

    public void setOrderID(Long orderId) {
        _orderID = orderId;
    }

    public static class OrderDetailBuilder{
        private Long _orderDetailID;
        private Long _orderID;
        private Long _dishID;
        private OrderDetailStatusTypeCode _status;
        private String _specialNote;

        OrderDetailBuilder(){
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

        public OrderDetailBuilder setSpecialNote(String specialNote){
            _specialNote = specialNote;
            return this;
        }

        public OrderDetail build(){
            return new OrderDetail(_orderDetailID, _orderID, _dishID, _status, _specialNote);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(_orderDetailID, that._orderDetailID) &&
                Objects.equals(_orderID, that._orderID) &&
                Objects.equals(_dishVerID, that._dishVerID) &&
                _status == that._status &&
                Objects.equals(_specialNote, that._specialNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(_orderDetailID, _orderID, _dishVerID, _status, _specialNote);
    }
}
