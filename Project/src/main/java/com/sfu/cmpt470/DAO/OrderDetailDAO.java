package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.pojo.OrderDetail;

import java.sql.SQLException;

public class OrderDetailDAO extends BaseDAO{
    OrderDetailDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public String updateOrderDetailStatus(OrderDetail orderDetail) throws SQLException {
        String error = verifyOrderDetail(orderDetail);
        if(StringUtil.isNullOrEmpty(error)){
            _db.supplyQuery("UPDATE order_details SET status = ? WHERE order_details_id = ?");
            _db.setString(orderDetail.getStatus().toString(),1);
            _db.setLong(orderDetail.getOrderDetailID(), 2);
            return "";
        }
        return error;
    }

    public String verifyOrderDetail(OrderDetail orderDetail){
        return "";
    }
}
