package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.service.DishService;
import com.sfu.cmpt470.service.DishServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/dish")
public class DishWebAPI {
    @Path("/allDishesForRestaurantID/{restaurantID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurantID") Long restaurantID ) {
        try {
            DishService dishService = new DishServiceImpl();
            return dishService.getDishesFor(restaurantID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/allDishesForRestaurantName/{restaurantName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurantName") String restaurantName ) {
        try {
            DishService dishService = new DishServiceImpl();
            return dishService.getDishesFor(restaurantName);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
