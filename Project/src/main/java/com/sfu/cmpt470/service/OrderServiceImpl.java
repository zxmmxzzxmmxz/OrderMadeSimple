package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.OrderDAO;

import com.sfu.cmpt470.pojo.Order;



import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class OrderServiceImpl implements OrderService{
    public String getAllOrders() throws IllegalArgumentException {

        /*OrderDAO dao = new OrderDAO();
        dao.getAllOrders();*/


        List<Order> orders = new ArrayList<Order>();
        OffsetDateTime current = OffsetDateTime.now();

        Order first = new Order(100, 1);
        first.set_time(current.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        orders.add(first);

        Order second = new Order(101, 2);
        second.set_time(current.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        orders.add(second);

        String json = new Gson().toJson(orders);
        return json;
    }

    public void addOrder(String order) {
        Gson gson = new Gson();
        Order current = gson.fromJson(order, Order.class);
        System.out.println("Add new order: " + "order id: " + current.getOrderId() + " restaurant id: " + current.get_restaurant_id());
    }
}
