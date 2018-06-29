package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.RestaurantDAO;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Error;

import java.sql.SQLException;

public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantDAO _dao;
    private Gson _gson = new Gson();

    public RestaurantServiceImpl(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _dao = new RestaurantDAO(connector);
    }

    @Override
    public String getAllRestaurants() {
        try {
            return _gson.toJson(_dao.findAllRestaurant());
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }
}
