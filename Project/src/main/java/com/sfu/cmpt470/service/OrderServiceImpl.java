package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.OrderDAO;

import com.sfu.cmpt470.pojo.Error;
import com.sfu.cmpt470.pojo.Order;


import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;


public class OrderServiceImpl implements OrderService{
    private OrderDAO _dao;
    private Gson _gson = new Gson();
    public OrderServiceImpl() throws SQLException, ClassNotFoundException {
        _dao = new OrderDAO();
    }

    public String getAllOrders() throws IllegalArgumentException {
        List<Order> orders;
        try {
            orders = _dao.getAllOrders();
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }

        return _gson.toJson(orders);
    }

    public String findOrder(long order_id){
        try {
            return _gson.toJson(_dao.findOrder(order_id));
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
    }

    public String addOrder(String order) {
        Order newOrder = _gson.fromJson(order, Order.class);
        newOrder.setTime(OffsetDateTime.now());
        try {
            _dao.addOrder(newOrder);
        } catch (SQLException e) {
            return _gson.toJson(new Error(e.toString()));
        }
        return "";
    }
}
