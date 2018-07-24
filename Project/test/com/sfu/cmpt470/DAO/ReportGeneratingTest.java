package com.sfu.cmpt470.DAO;


import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.*;


public class ReportGeneratingTest {

    private static final boolean flag = true;
    private DishDAO _dishDao;
    private RestaurantDAO _restaurantDao;
    private DatabaseConnector _con;
    private String _restaurantName;

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
    public void sampleTest(){
        assert (flag);
    }

}
