package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.DishService;
import com.sfu.cmpt470.service.DishServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/dish")
public class DishWebAPI {
    @Path("/allDishes/{restaurant_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurant_id") long restaurantID) {
        try {
            DishService dishService = new DishServiceImpl();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return dishService.getDishesFor(restaurantID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
