package com.sfu.cmpt470.database;

import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRowMapper implements RowMapper<Restaurant>{

    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Restaurant(rs.getLong("restaurant_id"), rs.getString("restaurant_name"));
    }
}
