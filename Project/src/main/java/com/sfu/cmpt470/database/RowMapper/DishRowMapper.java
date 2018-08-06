package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRowMapper implements RowMapper<Dish> {
    @Override
    public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dish.Builder builder = Dish.newBuilder();
        builder.setDishID(rs.getLong("dish_id"))
                .setDishVerID(rs.getLong("dish_ver_id"))
                .setDishName(rs.getString("dish_name"))
                .setDescription(rs.getString("description"))
                .setRestaurantName(rs.getString("restaurant_name"))
                .setPrice(rs.getFloat("price"))
                .setMenuFlag(rs.getString("menu_flag"))
                .setVersionNumber(rs.getInt("version_number"))
                .setIsDeleted(rs.getBoolean("deleted"));;
        return builder.build();
    }
}
