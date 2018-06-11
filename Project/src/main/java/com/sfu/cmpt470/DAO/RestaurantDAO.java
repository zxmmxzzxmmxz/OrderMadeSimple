package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.RestaurantRowMapper;
import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDAO extends BaseDAO {
    public RestaurantDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public ArrayList<Restaurant> findAllRestaurant() throws SQLException {
        _db.supplyQuery("SELECT restaurant_id, restaurant_name FROM restaurant");
        return _db.queryList(new RestaurantRowMapper());
    }

    public String findRestaurantByUsername(String username) throws SQLException {
        _db.supplyQuery("SELECT restaurant.restaurant_id, restaurant.restaurant_name FROM restaurant JOIN user_user ON user_user.restaurant_id = restaurant.restaurant_id WHERE user_user.username = ?");
        _db.setString(username,1);
        return _db.queryOneRecord(new RestaurantRowMapper()).getRestaurantName();
    }
}
