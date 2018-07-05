package com.sfu.cmpt470.database.RowMapper;

import com.sfu.cmpt470.pojo.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRowMapper implements RowMapper<Dish> {
    @Override
    public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dish.DishBuilder dishBuilder = new Dish.DishBuilder();
        return dishBuilder.setDishID(rs.getLong("dish_id"))
                .setDishName(rs.getString("dish_name"))
                .setRestaurant(rs.getString("restaurant_name"))
                .setDescription(rs.getString("description"))
                .setPrice(rs.getFloat("price"))
                .setMenuFlag(rs.getString("menu_flag"))
                .build();
    }
}
