package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.pojo.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/4/2018.
 */
public class OrderServiceImpl implements OrderService{
    public String SendOrder() throws IllegalArgumentException {
        List<Order> orders = new ArrayList<Order>();
        Order first = new Order();
        orders.add(first);
        Order second = new Order("Peter", "Strawberry slush");
        orders.add(second);

        String json = new Gson().toJson(orders);
        return json;
    }
}
