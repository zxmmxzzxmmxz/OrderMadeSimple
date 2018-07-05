package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.DishVerVal;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DishDAOIntTest {

    private static final String DISH_NAME = "fancy fish";
    private static final String DESCRIPTION = "fancy fish with some hot chilli oil which doesn't make sense at all";
    private static final String RESTAURANT_NAME = "joojak";
    private static final float PRICE = 14.99f;
    private static final String MENU_FLAG = "lunch special";


    private DishDAO _dishDao;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        _dishDao = new DishDAO(new DatabaseConnector());
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
        assert(verVal.getRestaurantName().equals(RESTAURANT_NAME));
        assert(verVal.getPrice() == PRICE);
        assert(verVal.getMenuFlag().equals(MENU_FLAG));
    }

    private Dish getNewDish() {
        Dish.DishBuilder dishBuilder = new Dish.DishBuilder();
        dishBuilder.setDishName(DISH_NAME)
                .setDescription(DESCRIPTION)
                .setRestaurant(RESTAURANT_NAME)
                .setPrice(PRICE)
                .setMenuFlag(MENU_FLAG);
        return dishBuilder.build();
    }
}
