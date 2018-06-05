package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.OrderDetailRowMapper;
import com.sfu.cmpt470.database.OrderRowMapper;
import com.sfu.cmpt470.pojo.Error;
import com.sfu.cmpt470.pojo.Order;
import com.sfu.cmpt470.pojo.OrderDetail;
import com.sfu.cmpt470.properties.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    public String getAllOrders() throws IllegalArgumentException {
        Gson gson = new Gson();
        DatabaseConnector db;
        List<Order> orders;
        try {
            db = new DatabaseConnector();
            System.out.println("Connection established");
            db.supplyQuery(Query.getAllOrder());
            orders = db.executeQueryList(new OrderRowMapper()).stream().map(e -> (Order) e).collect(Collectors.toList());
            db.supplyQuery(Query.findOrderDetails());
            for (Order order : orders) {
                db.setInt(order.getOrderId(),1);
                List<OrderDetail> orderDetails = db.executeQueryList(new OrderDetailRowMapper()).stream().map(e -> (OrderDetail) e).collect(Collectors.toList());
                order.setOrderDetails(orderDetails);
            }
            db.disconnect();
        } catch (SQLException | ClassNotFoundException e) {
            return gson.toJson(new Error(e.toString()));
        }
        return gson.toJson(orders);
    }
}
