package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.DishRowMapper;
import com.sfu.cmpt470.pojo.Dish;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAO extends BaseDAO {

    public DishDAO() {
        super();
    }

    public DishDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public ImmutableList<Dish> getDishesBy(String restaurantName) throws SQLException {
        ImmutableList<Long> disheIDs = getDishIDsFor(restaurantName);
        ImmutableList.Builder<Dish> result = ImmutableList.builder();
        for (Long disheID : disheIDs) {
            Dish foundDish = getDishByDishID(disheID);
            if(!foundDish.isDeleted()){
                result.add(foundDish);
            }
        }

        return result.build();
    }

    public Dish getDishByDishID(long dishID) throws SQLException {
        _db.supplyQuery("SELECT dish_ver.dish_id, dish_ver.dish_ver_id, dish_name, description, restaurant_name, price, menu_flag, version_number, deleted  " +
                "FROM dish_ver " +
                "JOIN dish ON dish_ver.dish_ver_id = dish.dish_ver_id " +
                "WHERE dish_ver.dish_id = ? " +
                "ORDER BY version_number DESC LIMIT 1");
        _db.setLong(dishID, 1);
        return _db.queryOneRecord(new DishRowMapper());
    }

    public Dish getDishByDishVerID(long dishVerID) throws SQLException {
        _db.supplyQuery("SELECT dish_ver.dish_id, dish_ver.dish_ver_id, dish_name, description, restaurant_name, price, menu_flag, version_number, deleted " +
                "FROM dish_ver " +
        "JOIN dish ON dish_ver.dish_ver_id = dish.dish_ver_id " +
                "WHERE dish_ver.dish_ver_id = ? ");
        _db.setLong(dishVerID, 1);
        return _db.queryOneRecord(new DishRowMapper());
    }

    public ImmutableList<Dish> getDishesByVerIDs(List<Long> verIDs) throws SQLException {
        _db.supplyQuery("SELECT dish_ver.dish_id, dish_ver.dish_ver_id, dish_name, description, restaurant_name, price, menu_flag, version_number, deleted  " +
                "FROM dish_ver " +
                "JOIN dish ON dish_ver.dish_ver_id = dish.dish_ver_id " +
                "WHERE dish_ver.dish_ver_id = ANY(?) ");
        _db.setArray(verIDs.toArray(), 1,"integer");
        return ImmutableList.copyOf(_db.queryList(new DishRowMapper()));
    }

    public ImmutableList<Long> getDishIDsFor(String restaurantName) throws SQLException{
        _db.supplyQuery("SELECT dish.dish_id as dish_id " +
                "FROM dish " +
                "JOIN dish_ver ON dish.dish_ver_id = dish_ver.dish_ver_id " +
                "JOIN restaurant ON restaurant.restaurant_name = dish_ver.restaurant_name " +
                "WHERE dish_ver.restaurant_name = ? " +
                "ORDER BY dish_id ASC");
        _db.setString(restaurantName, 1);
        ArrayList<Long> IDs = _db.queryList((rs, rowNum) -> rs.getLong("dish_id"));
        return ImmutableList.copyOf(IDs);
    }

    public Long createDish(Dish newDish) throws SQLException {
        _db.supplyQuery("INSERT INTO dish " +
                "(dish_ver_id) " +
                "VALUES(-1)");
        _db.executeUpdate();
        Long dishID = Iterables.getOnlyElement(_db.getInsertedKeys());
        newDish = newDish.toBuilder().setDishID(dishID).build();

        //create ver val
        Long dishVerID = createDishVerVal(newDish);
        //set verID in master table
        _db.supplyQuery("UPDATE dish SET dish_ver_id = ? WHERE dish_id = ?");
        _db.setLong(dishVerID, 1);
        _db.setLong(dishID, 2);
        _db.executeUpdate();

        return dishID;
    }

    public Long createDishVerVal(Dish newDish) throws SQLException {
        _db.supplyQuery("INSERT INTO dish_ver " +
                "(dish_id, dish_name, description, restaurant_name, price, menu_flag, version_number) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)");
        _db.setLong(newDish.getDishID(),1);
        _db.setString(newDish.getDishName(), 2);
        _db.setString(newDish.getDescription(), 3);
        _db.setString(newDish.getRestaurantName(), 4);
        _db.setFloat(newDish.getPrice(), 5);
        _db.setString(newDish.getMenuFlag(), 6);
        _db.setInt(1, 7);
        _db.executeUpdate();
        return Iterables.getOnlyElement(_db.getInsertedKeys());
    }

    public void updateDish(Dish dish) throws SQLException {

        Dish existingDish = getDishByDishID(dish.getDishID());
        _db.supplyQuery("INSERT INTO dish_ver " +
                "(dish_id, dish_name, description, restaurant_name, price, menu_flag, version_number) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)");
        _db.setLong(dish.getDishID(),1);
        _db.setString(dish.getDishName(), 2);
        _db.setString(dish.getDescription(), 3);
        _db.setString(dish.getRestaurantName(), 4);
        _db.setFloat(dish.getPrice(), 5);
        _db.setString(dish.getMenuFlag(), 6);
        _db.setInt(existingDish.getVersionNumber() + 1, 7);
        _db.executeUpdate();
        Long newVerValID = Iterables.getOnlyElement(_db.getInsertedKeys());
        _db.supplyQuery("UPDATE dish SET dish_ver_id = ? " +
                "WHERE dish_id = ?");
        _db.setLong(newVerValID, 1);
        _db.setLong(dish.getDishID(), 2);
        _db.executeUpdate();
      }

    public void deleteDish(Dish dish) throws SQLException {
        _db.supplyQuery("UPDATE dish SET deleted = true WHERE dish_id = ?");
        _db.setLong(dish.getDishID(), 1);
        _db.executeUpdate();
    }
}


