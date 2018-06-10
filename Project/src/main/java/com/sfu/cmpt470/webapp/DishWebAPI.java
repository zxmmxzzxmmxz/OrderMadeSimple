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
    @Secured
    @Path("/allDishes/{restaurant_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRestaurants(@Context HttpServletResponse response,
                                    @PathParam("restaurant_id") long restaurantID ) {
        try {
            DishService dishService = new DishServiceImpl();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return dishService.getDishesFor(restaurantID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
