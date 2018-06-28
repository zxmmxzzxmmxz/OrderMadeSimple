package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;
import com.sun.org.apache.bcel.internal.generic.NEW;

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

    public String getDishName(){
        return _dishName;
    }
    public void setOrderID(long orderId) {
        _orderID = orderId;
    }

    public static OrderDetailBuilder newBuilder(){
        return new OrderDetailBuilder();
    }

    public static class OrderDetailBuilder{
        private long _orderDetailID;
        private long _orderID;
        private long _dishID;
        private String _dishName;
        private OrderDetailStatusTypeCode _status;

        public OrderDetailBuilder(){
            _orderDetailID = -1;
            _orderID = -1;
            _dishID = -1;
            _dishName = "";
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

        public OrderDetailBuilder setDishID(long dishID){
            _dishID = dishID;
            return this;
        }

        public OrderDetailBuilder setDishName(String dishName){
            _dishName = dishName;
            return this;
        }

        public OrderDetailBuilder setStatus(OrderDetailStatusTypeCode status){
            _status = status;
            return this;
        }

        public OrderDetail build(){
            return new OrderDetail(_orderDetailID,_orderID,_dishID,_dishName,_status);
        }
    }
}
