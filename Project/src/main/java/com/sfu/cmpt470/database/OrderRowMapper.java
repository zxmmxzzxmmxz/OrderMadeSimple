package com.sfu.cmpt470.database;

import com.sfu.cmpt470.pojo.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class OrderRowMapper implements RowMapper{
    private static final String ORDER_ID = "order_id";
    private static final String RESTAURANT_ID = "restaurant_id";
    private static final String TIME = "time";

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(rs.getInt(ORDER_ID),rs.getInt(RESTAURANT_ID), rs.getObject(TIME,OffsetDateTime.class));
    }
}
