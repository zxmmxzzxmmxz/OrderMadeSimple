package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {
    private static final String ORDER_ID = "order_id";
    private static final String STATUS = "status";


    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetail.OrderDetailBuilder builder = OrderDetail.newBuilder();
        builder.setOrderID(rs.getLong(ORDER_ID))
                .setOrderDetailID(rs.getLong("order_details_id"))
                .setDishVerID(rs.getLong("dish_ver_id"))
                .setStatus(OrderDetailStatusTypeCode.of(rs.getString(STATUS)))
                .setSpecialNote(rs.getString("special_note"));
        return builder.build();
    }
}
