package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.DishDAO;
import com.sfu.cmpt470.DAO.RestaurantDAO;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.Error;
import jersey.repackaged.com.google.common.collect.ImmutableList;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DishServiceImpl implements DishService{
    private DishDAO _dao;
    private RestaurantDAO _restaurantDao;
    private Gson _gson = new Gson();
    public DishServiceImpl(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _dao = new DishDAO(connector);
        _restaurantDao = new RestaurantDAO(connector);
    }
    @Override
    public String getDishesFor(long restaurantID) {
        try {
            return _gson.toJson(_dao.getDishesBy(_restaurantDao.findRestaurantBy(restaurantID).getRestaurantName()));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    @Override
    public String getDishesFor(String restaurantName) {
        try {
            return _gson.toJson(_dao.getDishesBy(restaurantName));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    @Override
    public String getDish(long dishVerID) {
        try {
            return _gson.toJson(_dao.getDishByDishVerID(dishVerID));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    public String getDishByID(long dishID){
        try {
            return _gson.toJson(_dao.getDishByDishID(dishID));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    public String updateOrCreateDish(String dishString, CRUDMode mode) {
        Dish dish = _gson.fromJson(dishString, Dish.class);
        try {
            if(mode == CRUDMode.UPDATE){
                _dao.updateDish(dish);
                return "Dish Updated";
            }
            else if(mode == CRUDMode.CREATE){
                _dao.createDish(dish);
                return "Dish Created";
            }
            else if(mode == CRUDMode.DELETE){
                _dao.deleteDish(dish);
                return "Dish Deleted";
            }
            return "ONLY UPDATE OR CREATE OR DELETE SUPPORTED";


        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }

    }

    public String getDishesByDishVerID(List<Long> ids) {
        try {
            return _gson.toJson(_dao.getDishesByVerIDs(ids));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }
}
