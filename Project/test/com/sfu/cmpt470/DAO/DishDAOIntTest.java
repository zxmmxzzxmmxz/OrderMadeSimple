package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DishDAOIntTest {

    private static final String DISH_NAME = "fancy fish";
    private static final String NEW_DISH_NAME = "not a fancy fish";
    private static final String DESCRIPTION = "fancy fish with some hot chilli oil which doesn't make sense at all";
    private static final String NEW_DESCRIPTION = "not a fancy fish with non spicy chilli oil";
    private static final float PRICE = 14.99f;
    private static final float NEW_PRICE = 15.99f;
    private static final String MENU_FLAG = "lunch special";
    private static final String NEW_MENU_FLAG = "w/e special";

    private String _restaurantName;


    private DishDAO _dishDao;
    private RestaurantDAO _restaurantDao;
    private DatabaseConnector _con;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        _con = new DatabaseConnector();
        _dishDao = new DishDAO(_con);
        _restaurantDao = new RestaurantDAO(_con);
        Long restaurant = TestHelper.createRestaurant(_restaurantDao);
        _restaurantName = _restaurantDao.findRestaurantBy(restaurant).getRestaurantName();
    }

    @After
    public void complete() throws SQLException {
        _con.disconnect();
    }

    @Test
    public void createDish_shouldCreateDishAndVerVal_success() throws SQLException {
        Dish newDish = getNewDish(_restaurantName);
        Long dishID = _dishDao.createDish(newDish);
        assert(dishID != null && dishID > 1);
    }

    @Test
    public void getVerVal_withValidDishID_shouldReturnCorrectVerVal() throws SQLException {
        Dish newDish = getNewDish(_restaurantName);
        Long dishID = _dishDao.createDish(newDish);
        Dish verVal = _dishDao.getDishByDishID(dishID);
        assert(verVal.getDishID() == dishID);
        assert(verVal.getDishVerID() > 1);
        assert(verVal.getVersionNumber() == 1);
        assert(verVal.getDishName().equals(DISH_NAME));
        assert(verVal.getDescription().equals(DESCRIPTION));
        assert(verVal.getRestaurantName().equals(_restaurantName));
        assert(verVal.getPrice() == PRICE);
        assert(verVal.getMenuFlag().equals(MENU_FLAG));
    }

    private Dish getNewDish(String restaurantName) {
        Dish.Builder dishBuilder = Dish.newBuilder();
        dishBuilder.setDishName(DISH_NAME)
                .setDescription(DESCRIPTION)
                .setRestaurantName(restaurantName)
                .setPrice(PRICE)
                .setMenuFlag(MENU_FLAG);
        return dishBuilder.build();
    }

    @Test
    public void getDishIDsFor_newRestaurantAndDish_shouldReturnCorrectDishIDs() throws SQLException {
        Dish newDish = getNewDish(_restaurantName);
        Long dishID = _dishDao.createDish(newDish);
        ImmutableList<Dish> dishes = _dishDao.getDishesBy(_restaurantName);
        assert(dishes.size() == 1);
        Dish dish = Iterables.getOnlyElement(dishes);
        assert(dish.getDishID() == dishID);
        assert(dish.getDishName().equals(DISH_NAME));
        assert(dish.getDescription().equals(DESCRIPTION));
        assert(dish.getRestaurantName().equals(_restaurantName));
        assert(dish.getPrice() == PRICE);
        assert(dish.getMenuFlag().equals(MENU_FLAG));
    }

    @Test
    public void updateDish_newValidDish_shouldCreateNewVerValAndUpdateVal() throws SQLException {
        Dish dish = getNewDish(_restaurantName);
        Long dishID = _dishDao.createDish(dish);
        Dish createdDish = _dishDao.getDishByDishID(dishID);
        Dish newDish = createdDish.toBuilder().setDishName(NEW_DISH_NAME)
                .setDescription(NEW_DESCRIPTION)
                .setPrice(NEW_PRICE)
                .setMenuFlag(NEW_MENU_FLAG)
                .build();

        _dishDao.updateDish(newDish);

        Dish result = _dishDao.getDishByDishID(dishID);

        assert(result.getDishName().equals(NEW_DISH_NAME));
        assert(result.getDescription().equals(NEW_DESCRIPTION));
        assert(result.getRestaurantName().equals(_restaurantName));
        assert(result.getPrice() == NEW_PRICE);
        assert(result.getMenuFlag().equals(NEW_MENU_FLAG));
    }
}
