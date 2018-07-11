package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.service.OrderService;
import com.sfu.cmpt470.service.OrderServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderWebApp {

    //@Secured
    @Path("/allOrders/{restaurantName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllOrders(@Context HttpServletResponse response,
                               @PathParam("restaurantName")String restaurantName) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            OrderService orderService = new OrderServiceImpl(con);
            response.setHeader("Access-Control-Allow-Origin", "*");

            String result = orderService.getAllOpenOrders(restaurantName);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/getOrder/{order_id}")
    //@Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findOrder(@Context HttpServletResponse response,
                            @PathParam("order_id") long orderID) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            OrderService orderService = new OrderServiceImpl(con);
            response.setHeader("Access-Control-Allow-Origin", "*");
            String result = orderService.findOrder(orderID);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(String order) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            OrderService orders = new OrderServiceImpl(con);
            String result = orders.addOrder(order);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }

    @Path("/updateOrder")
    @POST
    //@Secured
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(String order) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            OrderService orders = new OrderServiceImpl(con);
            String result = orders.updateOrder(order);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }
}
