package com.sfu.cmpt470.service;

/**
 * Created by Administrator on 6/4/2018.
 */
public interface OrderService {
    String getAllOrders() throws IllegalArgumentException;
    String addOrder(String order);
    String findOrder(long order_id);

}
