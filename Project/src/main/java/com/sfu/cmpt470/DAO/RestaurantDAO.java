package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.RestaurantRowMapper;
import com.sfu.cmpt470.pojo.Restaurant;
import jersey.repackaged.com.google.common.collect.Iterables;

import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDAO extends BaseDAO {

    public RestaurantDAO(){
        super();
    }

    public RestaurantDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public ArrayList<Restaurant> findAllRestaurant() throws SQLException {
        _db.supplyQuery("SELECT restaurant_id, restaurant_name FROM restaurant");
        ArrayList<Restaurant> restaurants = _db.queryList(new RestaurantRowMapper());
        return restaurants;
    }

    public long findRestaurantIdByUsername(String username) throws SQLException {
        _db.supplyQuery("SELECT restaurant.restaurant_id, restaurant.restaurant_name FROM restaurant JOIN user_user ON user_user.restaurant_id = restaurant.restaurant_id WHERE user_user.username = ?");
        _db.setString(username,1);
        long id = _db.queryOneRecord(new RestaurantRowMapper()).getRestaurantId();
        return id;
    }

    public String findRestaurantByUsername(String username) throws SQLException {
        _db.supplyQuery("SELECT restaurant.restaurant_id, restaurant.restaurant_name FROM restaurant JOIN user_user ON user_user.restaurant_id = restaurant.restaurant_id WHERE user_user.username = ?");
        _db.setString(username,1);
        String restaurantName = _db.queryOneRecord(new RestaurantRowMapper()).getRestaurantName();
        return restaurantName;
    }

    public Long create(Restaurant newRestaurant) throws SQLException {
        _db.supplyQuery("INSERT INTO restaurant " +
                "(restaurant_name) " +
                "VALUES(?)");
        _db.setString(newRestaurant.getRestaurantName(), 1);
        _db.executeUpdate();
        return Iterables.getOnlyElement(_db.getInsertedKeys());
    }

    public Restaurant findRestaurantBy(long restaurantID) throws SQLException {
        _db.supplyQuery("SELECT restaurant_id, restaurant_name FROM restaurant " +
                "WHERE restaurant_id = ?");
        _db.setFloat(restaurantID, 1);
        return _db.queryOneRecord(new RestaurantRowMapper());
    }
}
