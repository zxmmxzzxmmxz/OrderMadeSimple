package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.OrderDetailRowMapper;
import com.sfu.cmpt470.database.RowMapper.OrderRowMapper;
import com.sfu.cmpt470.pojo.Order;
import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;
import com.sfu.cmpt470.properties.Query;
import jersey.repackaged.com.google.common.collect.Iterables;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO {

    DishDAO _dishDAO;

    public OrderDAO(){
        super();
    }
    public OrderDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
        _dishDAO = new DishDAO(connector);
    }
    public List<Order> getAllOrdersByRestaurantName(String restaurantName) throws IllegalArgumentException, SQLException {
        List<Order> orders;
        _db.supplyQuery("SELECT order_order.order_id, " +
                    "order_order.restaurant_name, " +
                    "order_order.time, " +
                    "order_order.status, " +
                    "order_order.table_number, " +
                    "order_order.included_in_eod_report, " +
                    "order_order.created_by_user " +
                    " FROM order_order WHERE order_order.restaurant_name = ?");
            _db.setString(restaurantName,1);
            orders = _db.queryList(new OrderRowMapper());
            _db.supplyQuery("SELECT order_details.order_id," +
                    "order_details.order_details_id," +
                    "order_details.dish_ver_id," +
                    "order_details.order_detail_status, " +
                    "order_details.special_note " +
                    "FROM order_details WHERE order_id = ?");
            for (Order order : orders) {
                _db.setLong(order.getOrderId(),1);
                List<OrderDetail> orderDetails = _db.queryList(new OrderDetailRowMapper());
                order.addOrderDetails(orderDetails);
            }
            return orders;
    }

    public Order getOrder(long orderID) throws SQLException {
        _db.supplyQuery("SELECT order_order.order_id, " +
                "order_order.restaurant_name, " +
                "order_order.time, " +
                "order_order.status, " +
                "order_order.table_number, " +
                "order_order.included_in_eod_report, " +
                "order_order.created_by_user, " +
                "order_details.order_id, " +
                "order_details.order_details_id," +
                "order_details.dish_ver_id, " +
                "order_details.order_detail_status," +
                "order_details.special_note  " +
                "FROM order_order LEFT OUTER JOIN order_details ON order_order.order_id = order_details.order_id " +
                "WHERE order_order.order_id = ?");
        _db.setLong(orderID,1);
        Order order = _db.queryList(new OrderRowMapper()).get(0);
        order = order.toBuilder().set_orderDetails(_db.queryList(new OrderDetailRowMapper())).build();
        return order;
    }



    public long createOrder(Order newOrder) throws SQLException {
        ArrayList<String> verificationError = verifyNewOrder(newOrder);
        if(verificationError.isEmpty()){
            _db.supplyQuery("INSERT INTO order_order (time,restaurant_name,status, table_number, included_in_eod_report, created_by_user) VALUES(?,?,?,?,?,?)");
            _db.setTime(newOrder.getTime(),1);
            _db.setString(newOrder.getRestaurantName(),2);
            _db.setString(newOrder.getOrderStatus(),3);
            _db.setString(newOrder.getTableNumber(), 4);
            _db.setBoolean(newOrder.shouldIncludedInEodReport(), 5);
            _db.setLong(newOrder.getCreatedByUser(), 6);
            _db.executeUpdate();
            Long orderID = Iterables.getOnlyElement(_db.getInsertedKeys());
            newOrder = newOrder.toBuilder().set_order_id(getOrder(orderID).getOrderId()).build();
            createOrderDetails(newOrder);
            return orderID;
        }
        else{
            throw new SQLException(verificationError.toString());
        }
    }

    private void createOrderDetails(Order newOrder) throws SQLException {
        List<OrderDetail> orderDetails = newOrder.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setStatus(OrderDetailStatusTypeCode.NEW);
            _db.supplyQuery("INSERT INTO order_details (order_id, dish_ver_id, order_detail_status, special_note) SELECT ?,dish_ver_id,?,? FROM dish_ver where dish_ver_id = ?;");
            _db.setLong(orderDetail.getOrderId(),1);
            _db.setLong(orderDetail.getDishVerId(), 4);
            _db.setString(orderDetail.getStatus().toString(), 2);
            _db.setString(orderDetail.getSpecialNote(), 3);
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
            _db.supplyQuery("SELECT dish_ver.restaurant_name FROM dish_ver JOIN restaurant ON dish_ver.restaurant_name = restaurant.restaurant_name where dish_ver.dish_ver_id = ANY(?)");
            _db.setArray(order.getOrderDetails().stream().map(OrderDetail::getDishVerId).toArray(), 1,"integer");
            List<String> restaurantNames = _db.queryList((rs, rowNum) -> rs.getString("restaurant_name"));
            if(!restaurantNames.stream().allMatch(s -> s.equals(order.getRestaurantName()))){
                error.add("message:restaurant name must match through the order");
            }
        } catch (SQLException e) {
                error.add(e.getMessage());
        }

        return error;
    }

    public void updateOrder(Order newOrder) throws SQLException {
        //only update status for now
        Order order = getOrder(newOrder.getOrderId());
        _db.supplyQuery("UPDATE order_order SET status = ? WHERE order_order.order_id = ?");
        _db.setString(newOrder.getOrderStatus(),1);
        _db.setLong(order.getOrderId(),2);
        _db.executeUpdate();
    }
}
