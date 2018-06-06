package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.OrderService;
import com.sfu.cmpt470.service.OrderServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 6/4/2018.
 */
@Path("/orders")
public class OrderWebApp {
    @Path("/allOrders")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrders(@Context HttpServletResponse response){
        try{
            OrderService orderService = new OrderServiceImpl();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return orderService.getAllOrders();
        }catch(Exception e){
            return e.getMessage();
        }

    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(String order) throws Exception{
        try{
            OrderService orders = new OrderServiceImpl();
            if(orders != null){
                orders.addOrder(order);
                return Response.ok().build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (Exception e){
            return Response.status(500).entity("Server can not process the request").build();
        }


    }
}
