package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.service.OrderService;
import com.sfu.cmpt470.service.OrderServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderWebApp {

    @Secured
    @Path("/allOrders/{restaurantName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllOrders(@Context HttpServletResponse response,
                               @PathParam("restaurantName")String restaurantName) {
        try {
            OrderService orderService = new OrderServiceImpl();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return orderService.getAllOpenOrders(restaurantName);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/findOrder/{order_id}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findOrder(@Context HttpServletResponse response,
                            @PathParam("order_id") long orderID) {
        try {
            OrderService orderService = new OrderServiceImpl();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return orderService.findOrder(orderID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(String order) {
        try {
            OrderService orders = new OrderServiceImpl();
            String result = orders.addOrder(order);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }

    @Path("/updateOrder")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(String order) {
        try {
            OrderService orders = new OrderServiceImpl();
            String result = orders.updateOrder(order);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }

}
