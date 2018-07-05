package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.DishVerVal;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DishDAOIntTest {

    private static final String DISH_NAME = "fancy fish";
    private static final String DESCRIPTION = "fancy fish with some hot chilli oil which doesn't make sense at all";
    private static final float PRICE = 14.99f;
    private static final String MENU_FLAG = "lunch special";

    private String _restaurantName;


    private DishDAO _dishDao;
    private RestaurantDAO _restaurantDao;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        DatabaseConnector con = new DatabaseConnector();
        _dishDao = new DishDAO(con);
        _restaurantDao = new RestaurantDAO(con);
        Long restaurant = TestHelper.createRestaurant(_restaurantDao);
        _restaurantName = _restaurantDao.findRestaurantBy(restaurant).getRestaurantName();
    }

    @Test
    public void createDish_shouldCreateDishAndVerVal_success() throws SQLException {
        Dish newDish = getNewDish();
        Long dishID = _dishDao.createDish(newDish);
        assert(dishID != null && dishID > 1);
    }

    @Test
    public void getVerVal_withValidDishID_shouldReturnCorrectVerVal() throws SQLException {
        Dish newDish = getNewDish();
        Long dishID = _dishDao.createDish(newDish);
        DishVerVal verVal = _dishDao.getVerVal(dishID);
        assert(verVal.getDishID() == dishID);
        assert(verVal.getDishVerID() > 1);
        assert(verVal.getDishName().equals(DISH_NAME));
        assert(verVal.getDescription().equals(DESCRIPTION));
        assert(verVal.getRestaurantName().equals(_restaurantName));
        assert(verVal.getPrice() == PRICE);
        assert(verVal.getMenuFlag().equals(MENU_FLAG));
    }

    private Dish getNewDish() {
        Dish.DishBuilder dishBuilder = new Dish.DishBuilder();
        dishBuilder.setDishName(DISH_NAME)
                .setDescription(DESCRIPTION)
                .setRestaurant(_restaurantName)
                .setPrice(PRICE)
                .setMenuFlag(MENU_FLAG);
        return dishBuilder.build();
    }

    @Test
    public void getDishIDsFor_newRestaurantAndDish_shouldReturnCorrectDishIDs() throws SQLException {
        Dish newDish = getNewDish();
        Long dishID = _dishDao.createDish(newDish);
        ImmutableList<Dish> dishes = _dishDao.findDishesBy(_restaurantName);
        assert(dishes.size() == 1);
        Dish dish = Iterables.getOnlyElement(dishes);
        assert(dish.getDishID() == dishID);
        assert(dish.getDishName().equals(DISH_NAME));
        assert(dish.getDescription().equals(DESCRIPTION));
        assert(dish.getRestaurantName().equals(_restaurantName));
        assert(dish.getPrice() == PRICE);
        assert(dish.getMenuFlag().equals(MENU_FLAG));
    }
}
