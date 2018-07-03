package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.OrderDetailRowMapper;
import com.sfu.cmpt470.pojo.Order;
import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;

import java.sql.SQLException;

public class OrderDetailDAO extends BaseDAO {

    public OrderDetailDAO(){
        super();
    }

    public OrderDetailDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public String updateOrderDetailStatus(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String error = verifyOrderDetail(orderDetail);
        if (StringUtil.isNullOrEmpty(error)) {
            _db.supplyQuery("UPDATE order_details SET status = ? WHERE order_details_id = ?");
            _db.setString(orderDetail.getStatus().toString(), 1);
            _db.setLong(orderDetail.getOrderDetailID(), 2);
            _db.executeUpdate();
            Order order = new OrderDAO(_db).findOrder(orderDetail.getOrderId());
            if (order.getOrderDetails().stream().allMatch(orderDetail1 -> orderDetail1.getStatus() == OrderDetailStatusTypeCode.DONE)) {
                _db.supplyQuery("UPDATE order_order SET status = ? WHERE order_id = ?");
                _db.setString("done", 1);
                _db.setLong(orderDetail.getOrderId(), 2);
                _db.executeUpdate();
            }
            return "Order Detail Updated";
        }
        return error;
    }

    String verifyOrderDetail(OrderDetail orderDetail) throws SQLException {
        _db.supplyQuery("SELECT * FROM order_details WHERE order_details_id = ?");
        _db.setLong(orderDetail.getOrderDetailID(), 1);
        try {
            _db.queryOneRecord(new OrderDetailRowMapper());
        } catch (SQLException e) {
            return "order detail does not exist";
        }
        return "";
    }
}
