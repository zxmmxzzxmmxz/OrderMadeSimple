package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.DishVerVal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishVerValRowMapper implements RowMapper<DishVerVal> {
    @Override
    public DishVerVal mapRow(ResultSet rs, int rowNum) throws SQLException {
        DishVerVal.Builder builder = DishVerVal.newBuilder();
        builder.setDishID(rs.getLong("dish_id"))
                .setDishVerID(rs.getLong("dish_ver_id"))
                .setDishName(rs.getString("dish_name"))
                .setDescription(rs.getString("description"))
                .setRestaurantName(rs.getString("restaurant_name"))
                .setPrice(rs.getFloat("price"))
                .setMenuFlag(rs.getString("menu_flag"));
        return builder.build();
    }
}
