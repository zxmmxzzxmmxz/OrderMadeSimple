package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.DishRowMapper;
import com.sfu.cmpt470.database.RowMapper.DishVerValRowMapper;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.DishVerVal;
import com.sfu.cmpt470.pojo.ValBridge.DishBridge;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;

import java.sql.SQLException;
import java.util.ArrayList;

public class DishDAO extends BaseDAO {

    public DishDAO() {
        super();
    }

    public DishDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public ArrayList<Dish> findDishesForRestaurant(long restaurantID) throws SQLException {
        _db.supplyQuery("SELECT dish_id, dish_name, description, restaurant_id, price, menu_flag \n" +
                "FROM dish\n" +
                "WHERE restaurant_id = ?");

        _db.setLong(restaurantID, 1);
        ArrayList<Dish> result = _db.queryList(new DishRowMapper());
        return result;
    }

    public ArrayList<Dish> findDishesForRestaurant(String restaurantName) throws SQLException {
        _db.supplyQuery("SELECT dish.dish_id, dish.dish_name, dish.description, dish.restaurant_name, dish.price, dish.menu_flag \n" +
                "FROM dish\n" +
                "WHERE dish.restaurant_name = ?");

        _db.setString(restaurantName, 1);
        ArrayList<Dish> result = _db.queryList(new DishRowMapper());
        return result;
    }

    public Dish findDish(long dishID) throws SQLException {
        _db.supplyQuery("SELECT dish.dish_id, dish.dish_name, dish.description, dish.restaurant_name, dish.price, dish.menu_flag \n" +
                "FROM dish\n" +
                "WHERE dish.dish_id = ?");
        _db.setLong(dishID,1);
        return _db.queryOneRecord(new DishRowMapper());
    }

    public ImmutableList<Dish> findDishesBy(String restaurantName) throws SQLException {
        ImmutableList<Long> disheIDs = getDishIDsFor(restaurantName);
        ImmutableList.Builder<Dish> result = ImmutableList.builder();
        for (Long disheID : disheIDs) {
            result.add(DishBridge.toDishFrom(getVerVal(disheID)));
        }
        return result.build();
    }

    public DishVerVal getVerVal(long dishID) throws SQLException {
        _db.supplyQuery("SELECT dish_id, dish_ver_id, dish_name, description, restaurant_name, price, menu_flag " +
                "FROM dish_ver " +
                "WHERE dish_id = ?");
        _db.setLong(dishID, 1);
        return _db.queryOneRecord(new DishVerValRowMapper());
    }

    public ImmutableList<Long> getDishIDsFor(String restaurantName) throws SQLException{
        _db.supplyQuery("SELECT dish_id " +
                "FROM dish " +
                "JOIN dish_ver ON dish.dish_ver_id = dish_ver.dish_ver_id " +
                "JOIN restaurant ON restaurant.restaurant_name = dish_ver.restaurant_name " +
                "WHERE restaurant_name = ?");
        _db.setString(restaurantName, 1);
        ArrayList<Long> IDs = _db.queryList((rs, rowNum) -> rs.getLong("dish_id"));
        return ImmutableList.copyOf(IDs);
    }

    public Long createDish(Dish newDish) throws SQLException {
        _db.supplyQuery("INSERT INTO dish " +
                "(dish_name, dish_ver_id, description, price) " +
                "VALUES(?,-1,?,?)");
        _db.setString(newDish.getDishName(),1);
        _db.setString(newDish.getDescription(), 2);
        _db.setFloat(newDish.getPrice(), 3);
        _db.executeUpdate();
        Long dishID = Iterables.getOnlyElement(_db.getInsertedKeys());

        //create ver val
        _db.supplyQuery("INSERT INTO dish_ver " +
                "(version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) " +
                "VALUES(1, ?, ?, ?, ?, ?, ?)");
        _db.setLong(dishID,1);
        _db.setString(newDish.getDishName(), 2);
        _db.setString(newDish.getDescription(), 3);
        _db.setString(newDish.getRestaurantName(), 4);
        _db.setFloat(newDish.getPrice(), 5);
        _db.setString(newDish.getMenuFlag(), 6);
        _db.executeUpdate();
        return dishID;
    }

    public void updateDish(Dish dish) throws SQLException {
        _db.supplyQuery("UPDATE dish SET dish_name = ?, description = ?, restaurant_name = ?, price = ?, menu_flag = ? WHERE dish_id = ?");
        _db.setString(dish.getDishName(),1);
        _db.setString(dish.getDescription(),2);
        _db.setString(dish.getRestaurantName(),3);
        _db.setFloat(dish.getPrice(),4);
        _db.setString(dish.getMenuFlag(),5);
        _db.setLong(dish.getDishID(), 6);
        _db.executeUpdate();
    }
}
