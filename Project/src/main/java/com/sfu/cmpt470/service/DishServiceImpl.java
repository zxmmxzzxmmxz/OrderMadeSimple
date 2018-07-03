package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.DishDAO;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.Error;

import java.sql.SQLException;

public class DishServiceImpl implements DishService{
    private DishDAO _dao;
    private Gson _gson = new Gson();
    public DishServiceImpl(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _dao = new DishDAO(connector);
    }
    @Override
    public String getDishesFor(long restaurantID) {
        try {
            return _gson.toJson(_dao.findDishesForRestaurant(restaurantID));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    @Override
    public String getDishesFor(String restaurantName) {
        try {
            return _gson.toJson(_dao.findDishesForRestaurant(restaurantName));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    @Override
    public String getDish(long dishID) {
        try {
            return _gson.toJson(_dao.findDish(dishID));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    public String updateDish(String dishString) {
        Dish dish = _gson.fromJson(dishString, Dish.class);
        try {
            _dao.updateDish(dish);
            return "Dish Updated";
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }

    }
}
