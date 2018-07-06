package com.sfu.cmpt470.properties;

public class Query {
    public static String getAllOrder(){
        return "SELECT order_id, restaurant_name, time, status FROM order_order WHERE order_order.restaurant_name = ?";
    }

    public static String findOrderDetails() {
        return "SELECT order_details_id, order_id, dish_ver_id, status FROM order_details WHERE order_id = ?";
    }

    public static String insertOrder(){
        return "INSERT INTO order_order (time,restaurant_name,status) VALUES(?,?,?)";
    }

    public static String insertOrderDetail() {
        //order id, status, dish_ver_id
        return "INSERT INTO order_details (order_id, dish_ver_id, status) SELECT ?,dish_ver_id,? FROM dish_ver where dish_ver_id = ?;";
    }
}
