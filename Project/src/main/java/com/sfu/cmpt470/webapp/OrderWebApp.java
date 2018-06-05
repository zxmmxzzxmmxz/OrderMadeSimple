package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.OrderService;
import com.sfu.cmpt470.service.OrderServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 6/4/2018.
 */
@Path("/orders")
public class OrderWebApp {
    @Path("/allOrders")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrders(){
        OrderService orderService = new OrderServiceImpl();
        return orderService.getAllOrders();
    }
}
