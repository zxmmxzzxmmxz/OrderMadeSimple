package com.sfu.cmpt470.service;

import java.sql.SQLException;

/**
 * Created by Administrator on 6/4/2018.
 */
public interface OrderService{
    String getAllOpenOrders(String restaurantName) throws IllegalArgumentException;
    String addOrder(String order);
    String findOrder(long order_id);
    String updateOrder(String order);

    String getAllOrders(String restaurantName) throws SQLException;
}
