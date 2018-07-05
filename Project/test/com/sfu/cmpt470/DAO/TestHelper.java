package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class TestHelper {
    public static Long createRestaurant(RestaurantDAO dao) throws SQLException {
        String restaurantName = "restaurant_"+ OffsetDateTime.now();
        return dao.create(new Restaurant(-1, restaurantName));
    }

}
