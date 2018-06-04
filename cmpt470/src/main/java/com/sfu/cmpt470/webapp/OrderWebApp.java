package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.OrderService;
import com.sfu.cmpt470.service.OrderServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * Created by Administrator on 6/4/2018.
 */
@Path("/orders")
public class OrderWebApp {
    @Path("/finish")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sendOrder(){
        OrderService orders = new OrderServiceImpl();
        return orders.SendOrder();
    }
}
