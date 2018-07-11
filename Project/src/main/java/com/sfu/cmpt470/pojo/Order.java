package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    @SerializedName("order_id")
    private Long _order_id;
    @SerializedName("restaurant_name")
    private String _restaurantName;
    @SerializedName("time")
    private String _time;
    @SerializedName("orderDetails")
    private List<OrderDetail> _orderDetails;
    @SerializedName("order_status")
    private String _orderStatus;
    @SerializedName("table_number")
    private String _tableNumber;
    @SerializedName("included_in_eod_report")
    private boolean _includedInEodReport;
    @SerializedName("created_by_user")
    private Long _createdByUser;


    private Order(){
    }

    public Order(Long order_id, String restaurantName, OffsetDateTime time, List<OrderDetail> orderDetails, String orderStatus, String tableNumber, boolean includedInEodReport, Long createdByUser) {
        _order_id = order_id;
        _restaurantName = restaurantName;
        _time = time.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        _orderDetails = orderDetails;
        _tableNumber = tableNumber;
        _orderStatus = orderStatus;
        _includedInEodReport = includedInEodReport;
        _createdByUser = createdByUser;
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

    public String getStringTime(){
        return _time;
    }

    public List<OrderDetail> getOrderDetails(){
        return new ArrayList<>(_orderDetails);
    }

    public String getOrderStatus() {
        return _orderStatus;
    }

    public Long getCreatedByUser() {
        return _createdByUser;
    }

    public String getTableNumber() {
        return _tableNumber;
    }
    public boolean shouldIncludedInEodReport() {
        return _includedInEodReport;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder(){
        Builder builder = newBuilder();
        builder.set_order_id(_order_id)
                .set_restaurantName(_restaurantName)
                .set_time(getTime())
                .set_includedInEodReport(_includedInEodReport)
                .set_orderDetails(_orderDetails)
                .set_orderStatus(_orderStatus)
                .set_tableNumber(_tableNumber)
                .setCreatedByUser(_createdByUser);

        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return _includedInEodReport == order._includedInEodReport &&
                Objects.equals(_order_id, order._order_id) &&
                Objects.equals(_restaurantName, order._restaurantName) &&
                Objects.equals(_time, order._time) &&
                Objects.equals(_orderDetails, order._orderDetails) &&
                Objects.equals(_orderStatus, order._orderStatus) &&
                Objects.equals(_tableNumber, order._tableNumber) &&
                Objects.equals(_createdByUser, order._createdByUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(_order_id, _restaurantName, _time, _orderDetails, _orderStatus, _tableNumber, _includedInEodReport, _createdByUser);
    }

    public void addOrderDetails(List<OrderDetail> orderDetails) {
        _orderDetails.addAll(orderDetails);
    }

    public static class Builder{
        private Long _order_id;
        private String _restaurantName;
        private OffsetDateTime _time;
        private List<OrderDetail> _orderDetails;
        private String _orderStatus;
        private String _tableNumber;
        private boolean _includedInEodReport;
        private Long createdByUser;

        public Builder(){
            _includedInEodReport = true;
            _orderStatus = "new";
            _orderDetails = new ArrayList<>();
        }

        public Builder set_order_id(Long _order_id) {
            this._order_id = _order_id;
            return this;
        }

        public Builder set_restaurantName(String _restaurantName) {
            this._restaurantName = _restaurantName;
            return this;
        }

        public Builder set_includedInEodReport(boolean _includedInEodReport) {
            this._includedInEodReport = _includedInEodReport;
            return this;
        }

        public Builder set_orderDetails(List<OrderDetail> _orderDetails) {
            this._orderDetails = _orderDetails;
            return this;
        }

        public Builder set_orderStatus(String _orderStatus) {
            this._orderStatus = _orderStatus;
            return this;
        }

        public Builder set_tableNumber(String _tableNumber) {
            this._tableNumber = _tableNumber;
            return this;
        }

        public Builder set_time(OffsetDateTime _time) {
            this._time = _time;
            return this;
        }

        public Builder setCreatedByUser(Long createdByUser) {
            this.createdByUser = createdByUser;
            return this;
        }

        public Builder addOrderDetail(OrderDetail detail){
            _orderDetails.add(detail);
            return this;
        }

        public Order build(){
            if(_order_id != null){
                for(OrderDetail detail : _orderDetails){
                    detail.setOrderID(_order_id);
                }
            }
            return new Order(_order_id,_restaurantName, _time,_orderDetails,_orderStatus,_tableNumber,_includedInEodReport,createdByUser);
        }
    }
}
