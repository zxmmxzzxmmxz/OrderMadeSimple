package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.Restaurant;

import java.sql.SQLException;
import java.time.OffsetDateTime;

public class TestHelper {
    private static final String DISH_NAME = "fancy fish";
    //private static final String NEW_DISH_NAME = "not a fancy fish";
    private static final String DESCRIPTION = "fancy fish with some hot chilli oil which doesn't make sense at all";
    //private static final String NEW_DESCRIPTION = "not a fancy fish with non spicy chilli oil";
    private static final float PRICE = 14.99f;
    //private static final float NEW_PRICE = 15.99f;
    private static final String MENU_FLAG = "lunch special";
    //private static final String NEW_MENU_FLAG = "w/e special";

    public static Long createRestaurant(RestaurantDAO dao) throws SQLException {
        String restaurantName = "restaurant_"+ OffsetDateTime.now();
        return dao.create(new Restaurant(-1, restaurantName));
    }

    public static Long createNewDish(DishDAO dao) throws SQLException, ClassNotFoundException {
        RestaurantDAO restaurantDAO = new RestaurantDAO(dao._db);
        Long restaurant = createRestaurant(restaurantDAO);
        String restaurantName = restaurantDAO.findRestaurantBy(restaurant).getRestaurantName();
        return createNewDish(dao, restaurantName);
    }

    public static Long createNewDish(DishDAO dao, String restaurantName) throws SQLException {
        return dao.createDish(getNewDish(restaurantName));
    }

    private static Dish getNewDish(String restaurantName) {
        Dish.Builder dishBuilder = Dish.newBuilder();
        dishBuilder.setDishName(DISH_NAME+OffsetDateTime.now())
                .setDescription(DESCRIPTION+OffsetDateTime.now())
                .setRestaurantName(restaurantName)
                .setPrice(PRICE)
                .setMenuFlag(MENU_FLAG+OffsetDateTime.now());
        return dishBuilder.build();
    }
}
