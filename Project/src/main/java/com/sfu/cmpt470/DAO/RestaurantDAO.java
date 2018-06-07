package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.RestaurantRowMapper;
import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDAO extends BaseDAO<Restaurant> {
    public RestaurantDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public ArrayList<Restaurant> findAllRestaurant() throws SQLException {
        _db.supplyQuery("SELECT restaurant_id, restaurant_name FROM restaurant");
        return _db.queryList(new RestaurantRowMapper());
    }
}
