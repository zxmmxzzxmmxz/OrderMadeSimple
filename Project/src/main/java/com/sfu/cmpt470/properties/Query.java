package com.sfu.cmpt470.properties;

public class Query {
    public static String getAllOrder(){
        return "SELECT order_id, restaurant_id, time FROM order_order";
    }

    public static String findOrderDetails() {
        return "SELECT order_id, dish_id, dish_name, status FROM order_details WHERE order_id = ?";
    }
}
