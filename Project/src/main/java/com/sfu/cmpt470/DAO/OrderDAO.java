package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.database.OrderDetailRowMapper;
import com.sfu.cmpt470.database.OrderRowMapper;
import com.sfu.cmpt470.pojo.Order;
import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;
import com.sfu.cmpt470.properties.Query;
import jersey.repackaged.com.google.common.collect.Iterables;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderDAO extends BaseDAO {
    public OrderDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    public List<Order> getAllOrders() throws IllegalArgumentException, SQLException {
        List<Order> orders;
        try {
            _db.supplyQuery(Query.getAllOrder());
            orders = _db.queryList(new OrderRowMapper());
            _db.supplyQuery(Query.findOrderDetails());
            for (Order order : orders) {
                _db.setLong(order.getOrderId(),1);
                List<OrderDetail> orderDetails = _db.queryList(new OrderDetailRowMapper());
                order.setOrderDetails(orderDetails);
            }
            return orders;
        } finally{
            disconnect();
        }
    }

    public Order findOrder(long orderID) throws SQLException {
        _db.supplyQuery("SELECT order_order.order_id, order_order.restaurant_id, order_order.time, order_details.order_details_id,order_details.dish_id, order_details.dish_name, order_details.status " +
                "FROM order_order LEFT OUTER JOIN order_details ON order_order.order_id = order_details.order_id " +
                "WHERE order_order.order_id = ?");
        _db.setLong(orderID,1);
        Order order = _db.queryOneRecord(new OrderRowMapper());
        order.setOrderDetails(_db.queryList(new OrderDetailRowMapper()));
        return order;
    }



    public void addOrder(Order newOrder) throws SQLException {
        String verificationError = verifyNewOrder(newOrder);
        if(StringUtil.isNullOrEmpty(verificationError)){
            _db.supplyQuery(Query.insertOrder());
            _db.setTime(newOrder.getTime(),1);
            _db.setLong(newOrder.getRestaurantId(),2);
            _db.executeUpdate();
            Long order_id = Iterables.getOnlyElement(_db.getInsertedKeys());
            newOrder.setOrderID(findOrder(order_id).getOrderId());
            createOrderDetails(newOrder);
        }
        else{
            throw new SQLException(verificationError);
        }
    }

    private void createOrderDetails(Order newOrder) throws SQLException {
        List<OrderDetail> orderDetails = newOrder.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setStatus(OrderDetailStatusTypeCode.NEW);
            _db.supplyQuery(Query.insertOrderDetail());
            _db.setLong(orderDetail.getOrderId(),1);
            _db.setLong(orderDetail.getDishId(), 3);
            _db.setString(orderDetail.getStatus().toString(), 2);
            _db.executeUpdate();
        }
    }

    private String verifyNewOrder(Order order){
        StringBuilder error = new StringBuilder();
        if(order.getRestaurantId() <= 0){
            error.append("message:restaurant id is invalid\n");
        }
        if(order.getTime().isAfter(OffsetDateTime.now())){
            error.append("message:order cannot be in future\n");
        }
        try {
            _db.supplyQuery("SELECT restaurant_id FROM restaurant where restaurant_id = ?");
            _db.setLong(order.getRestaurantId(), 1);
            if(order.getRestaurantId() != _db.queryOneRecord((rs, rowNum) -> rs.getInt("restaurant_id"))){
                error.append("message:restaurant id for new order is incorrect");
            }
        } catch (SQLException e) {
            error.append(e.getMessage());
        }

        return error.toString();
    }
}
