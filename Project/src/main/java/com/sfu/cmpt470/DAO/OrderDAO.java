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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderDAO extends BaseDAO {
    public OrderDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    public List<Order> getAllOrders(String restaurantName) throws IllegalArgumentException, SQLException {
        List<Order> orders;
            _db.supplyQuery(Query.getAllOrder());
            _db.setString(restaurantName,1);
            orders = _db.queryList(new OrderRowMapper());
            _db.supplyQuery(Query.findOrderDetails());
            for (Order order : orders) {
                _db.setLong(order.getOrderId(),1);
                List<OrderDetail> orderDetails = _db.queryList(new OrderDetailRowMapper());
                order.setOrderDetails(orderDetails);
            }
            return orders;
    }

    public Order findOrder(long orderID) throws SQLException {
        _db.supplyQuery("SELECT order_order.order_id, order_order.restaurant_name, order_order.time, order_details.order_details_id,order_details.dish_id, order_details.dish_name, order_details.status " +
                "FROM order_order LEFT OUTER JOIN order_details ON order_order.order_id = order_details.order_id " +
                "WHERE order_order.order_id = ?");
        _db.setLong(orderID,1);
        Order order = _db.queryOneRecord(new OrderRowMapper());
        order.setOrderDetails(_db.queryList(new OrderDetailRowMapper()));
        return order;
    }



    public void addOrder(Order newOrder) throws SQLException {
        ArrayList<String> verificationError = verifyNewOrder(newOrder);
        if(verificationError.isEmpty()){
            _db.supplyQuery(Query.insertOrder());
            _db.setTime(newOrder.getTime(),1);
            _db.setString(newOrder.getRestaurantName(),2);
            _db.setString("new",3);
            _db.executeUpdate();
            Long order_id = Iterables.getOnlyElement(_db.getInsertedKeys());
            newOrder.setOrderID(findOrder(order_id).getOrderId());
            createOrderDetails(newOrder);
        }
        else{
            throw new SQLException(verificationError.toString());
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

    ArrayList<String> verifyNewOrder(Order order){
        ArrayList<String> error = new ArrayList<>();
        if(StringUtil.isNullOrEmpty(order.getRestaurantName())){
            error.add("message:restaurant name must exist\n");
            return error;
        }
        if(order.getTime().isAfter(OffsetDateTime.now())){
            error.add("message:order cannot be in future\n");
            return error;
        }
        try {
            _db.supplyQuery("SELECT dish.restaurant_name FROM dish JOIN restaurant ON dish.restaurant_name = restaurant.restaurant_name where dish.dish_id = ANY(?)");
            _db.setArray(order.getOrderDetails().stream().map(OrderDetail::getDishId).toArray(), 1,"integer");
            String restaurantName = _db.queryOneRecord((rs, rowNum) -> rs.getString("restaurant_name"));
            if(!order.getRestaurantName().equals(restaurantName)){
                error.add("message:restaurant name must match through the order");
            }
        } catch (SQLException e) {
                error.add(e.getMessage());
        }

        return error;
    }

    public void updateOrder(Order newOrder) throws SQLException {
        //only update status for now
        Order order = findOrder(newOrder.getOrderId());
        _db.supplyQuery("UPDATE order_order SET status = ? WHERE order_order.order_id = ?");
        _db.setString(newOrder.getOrderStatus(),1);
        _db.setLong(order.getOrderId(),2);
        _db.executeUpdate();
    }
}
