package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {
    private static final String ORDER_ID = "order_id";
    private static final String DISH_ID = "dish_id";
    private static final String STATUS = "status";
    private static final String DISH_NAME = "dish_name";


    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderDetail(
                rs.getLong("order_details_id"),
                rs.getInt(ORDER_ID),
                rs.getInt(DISH_ID),
                rs.getString(DISH_NAME),
                OrderDetailStatusTypeCode.of(rs.getString(STATUS)));
    }
}
