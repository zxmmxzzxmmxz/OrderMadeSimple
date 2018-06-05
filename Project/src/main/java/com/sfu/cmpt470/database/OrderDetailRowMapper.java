package com.sfu.cmpt470.database;

import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper {
    private static final String ORDER_ID = "order_id";
    private static final String DISH_ID = "dish_id";
    private static final String STATUS = "status";
    private static final String DISH_NAME = "dish_name";

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderDetail(
                rs.getInt(ORDER_ID),
                rs.getInt(DISH_ID),
                rs.getString(DISH_NAME),
                OrderDetailStatusTypeCode.of(rs.getString(STATUS)));
    }
}
