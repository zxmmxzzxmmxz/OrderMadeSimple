package com.sfu.cmpt470.service;

import com.google.gson.Gson;
import com.sfu.cmpt470.DAO.OrderDetailDAO;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.OrderDetail;

import java.sql.SQLException;

public class OrderDetailServiceImpl {
    private OrderDetailDAO _dao;
    private Gson _gson = new Gson();

    public OrderDetailServiceImpl(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        _dao = new OrderDetailDAO(connector);
    }

    public String updateOrderDetail(String orderDetail){
        try {
            return _dao.updateOrderDetailStatus(_gson.fromJson(orderDetail,OrderDetail.class));
        } catch (SQLException | ClassNotFoundException e) {
            return _gson.toJson(new Error(e.getMessage()));
        }
    }
}
