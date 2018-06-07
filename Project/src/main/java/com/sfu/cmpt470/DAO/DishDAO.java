package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DishRowMapper;
import com.sfu.cmpt470.pojo.Dish;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAO extends BaseDAO<Dish>{

    public DishDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public ArrayList<Dish> findDishesForRestaurant(long restaurantID) throws SQLException {
        _db.supplyQuery("SELECT dish_id, dish_name, description, restaurant_id, price, menu_flag \n" +
                "FROM dish\n" +
                "WHERE restaurant_id = ?");

        _db.setLong(restaurantID, 1);
        return _db.queryList(new DishRowMapper());
    }
}
