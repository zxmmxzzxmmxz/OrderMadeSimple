package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.Order;
import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.pojo.OrderDetailStatusTypeCode;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.OffsetDateTime;

public class OrderDaoIntTest {
    private DishDAO _dishDao;
    private RestaurantDAO _restaurantDao;
    private OrderDAO _orderDao;
    private DatabaseConnector _con;

    private String _restaurantName;
    private Dish _dish;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        _con = new DatabaseConnector();
        _dishDao = new DishDAO(_con);
        _orderDao = new OrderDAO(_con);
        _restaurantDao = new RestaurantDAO(_con);
        Long restaurant = TestHelper.createRestaurant(_restaurantDao);
        _restaurantName = _restaurantDao.findRestaurantBy(restaurant).getRestaurantName();
        Long dishID = TestHelper.createNewDish(_dishDao, _restaurantName);
        _dish = _dishDao.getDishByDishID(dishID);
    }

    @After
    public void finish() throws SQLException {
        _con.disconnect();
    }

    @Test
    public void createOrder_newOrder_shouldCreateOrderAndOrderDetails() throws SQLException {
        Order order = new Order();
        order.setRestaurantName(_restaurantName);
        order.setTime(OffsetDateTime.now());
        OrderDetail detail = OrderDetail.newBuilder().setDishVerID(_dish.getDishVerID()).setStatus(OrderDetailStatusTypeCode.NEW).build();

        order.addOrderDetail(detail);
        long orderID = _orderDao.createOrder(order);

        Order createdOrder = _orderDao.getOrder(orderID);

        assert(orderID == createdOrder.getOrderId());
        assert(createdOrder.getOrderDetails().size() == 1);
        OrderDetail resultDetail = Iterables.getOnlyElement(createdOrder.getOrderDetails());
        assert(resultDetail.getStatus().equals(OrderDetailStatusTypeCode.NEW));
        assert(resultDetail.getOrderId() == orderID);
        assert(resultDetail.getOrderDetailID() >= 1);
        assert(resultDetail.getDishVerId() == _dish.getDishVerID());
    }

    @Test
    public void getAllOrdersByRestaurantName_newOrder_shouldFetchCorrectOrder() throws SQLException {
        Order order = new Order();
        order.setRestaurantName(_restaurantName);
        order.setTime(OffsetDateTime.now());
        OrderDetail detail = OrderDetail.newBuilder().setDishVerID(_dish.getDishVerID()).setStatus(OrderDetailStatusTypeCode.NEW).build();

        order.addOrderDetail(detail);
        long orderID = _orderDao.createOrder(order);

        Order createdOrder = _orderDao.getOrder(orderID);

        Order result = Iterables.getOnlyElement(_orderDao.getAllOrdersByRestaurantName(_restaurantName));

        assert(createdOrder.equals(result));

    }

    @Test
    public void getOrder_multipleOrderDetails_shouldReturnOneOrder() throws SQLException {
        Order order = new Order();
        order.setRestaurantName(_restaurantName);
        order.setTime(OffsetDateTime.now());
        OrderDetail detail = OrderDetail.newBuilder().setDishVerID(_dish.getDishVerID()).setStatus(OrderDetailStatusTypeCode.NEW).build();
        order.addOrderDetail(detail);
        order.addOrderDetail(detail.toBuilder().build());

        long orderID = _orderDao.createOrder(order);

        Order createdOrder = _orderDao.getOrder(orderID);

        Order result = Iterables.getOnlyElement(_orderDao.getAllOrdersByRestaurantName(_restaurantName));

        assert(createdOrder.equals(result));
    }
}
