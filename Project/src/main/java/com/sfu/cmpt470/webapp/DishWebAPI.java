package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.service.DishService;
import com.sfu.cmpt470.service.DishServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dish")
public class DishWebAPI {
    @SuppressWarnings("Duplicates")
    @Path("/allDishesForRestaurantID/{restaurantID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurantID") Long restaurantID ) {
        DatabaseConnector con;
        try {
            con = new DatabaseConnector();
            DishService dishService = new DishServiceImpl(con);
            String result = dishService.getDishesFor(restaurantID);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @SuppressWarnings("Duplicates")
    @Path("/allDishesForRestaurantName/{restaurantName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurantName") String restaurantName ) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishService dishService = new DishServiceImpl(con);
            String result = dishService.getDishesFor(restaurantName);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/findDish/{dishID}")
    @GET
    //@Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findDish(@Context HttpServletResponse response,
                                    @PathParam("dishID") long dishID ) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishService dishService = new DishServiceImpl(con);
            String result = dishService.getDish(dishID);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/updateDish")
    @POST
    //@Secured
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateDish(String dish){
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl service = new DishServiceImpl(con);
            String result = service.updateDish(dish);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }


}
