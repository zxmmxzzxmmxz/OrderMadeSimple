package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RestaurantRowMapper;
import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDAO extends BaseDAO {
    public RestaurantDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public ArrayList<Restaurant> findAllRestaurant() throws SQLException {
        _db.supplyQuery("SELECT restaurant_id, restaurant_name FROM restaurant");
        ArrayList<Restaurant> restaurants = _db.queryList(new RestaurantRowMapper());
        return restaurants;
    }

    public String findRestaurantByUsername(String username) throws SQLException {
        _db.supplyQuery("SELECT restaurant.restaurant_id, restaurant.restaurant_name FROM restaurant JOIN user_user ON user_user.restaurant_id = restaurant.restaurant_id WHERE user_user.username = ?");
        _db.setString(username,1);
        String restaurantName = _db.queryOneRecord(new RestaurantRowMapper()).getRestaurantName();
        return restaurantName;
    }
}
