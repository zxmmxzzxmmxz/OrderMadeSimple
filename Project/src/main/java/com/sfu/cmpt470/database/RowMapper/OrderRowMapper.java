package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class OrderRowMapper implements RowMapper<Order>{
    private static final String ORDER_ID = "order_id";
    private static final String RESTAURANT_NAME = "restaurant_name";
    private static final String TIME = "time";

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order.Builder builder = Order.newBuilder();
        builder.set_order_id(rs.getLong(ORDER_ID))
                .set_restaurantName(rs.getString(RESTAURANT_NAME))
                .set_time(rs.getObject(TIME,OffsetDateTime.class))
                .set_orderStatus(rs.getString("status"))
                .set_includedInEodReport(rs.getBoolean("included_in_eod_report"))
                .set_tableNumber(rs.getString("table_number"))
                .setCreatedByUser(rs.getLong("created_by_user"));
        return builder.build();
    }
}
