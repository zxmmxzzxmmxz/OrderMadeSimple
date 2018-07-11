package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.OrderDAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Error;
import com.sfu.cmpt470.pojo.Order;


import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class OrderServiceImpl implements OrderService{
    private OrderDAO _dao;
    private Gson _gson = new Gson();
    public OrderServiceImpl(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _dao = new OrderDAO(connector);
    }

    public String getAllOpenOrders(String restaurantName) throws IllegalArgumentException {
        //in progress or new
        List<Order> orders;
        try {
            orders = getAllOrders(restaurantName).stream().filter(order -> !order.getOrderStatus().equals("done")).collect(Collectors.toList());
            orders = orders.stream().filter(order -> !order.getOrderDetails().isEmpty()).collect(Collectors.toList());
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }

        return _gson.toJson(orders);
    }

    private List<Order> getAllOrders(String restaurantName) throws SQLException {
        return _dao.getAllOrdersByRestaurantName(restaurantName);
    }

    public String findOrder(long order_id){
        try {
            return _gson.toJson(_dao.getOrder(order_id));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    @Override
    public String updateOrder(String order) {
        Order newOrder = _gson.fromJson(order, Order.class);
        try {
            _dao.updateOrder(newOrder);
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
        return "";
    }

    public String addOrder(String order) {
        Order newOrder = _gson.fromJson(order, Order.class);
        newOrder.setTime(OffsetDateTime.now());
        try {
            _dao.createOrder(newOrder);
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
        return "";
    }
}
