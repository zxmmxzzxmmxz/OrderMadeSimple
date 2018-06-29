package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.service.RestaurantService;
import com.sfu.cmpt470.service.RestaurantServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/restaurant")
public class RestaurantWebAPI {

    @Path("/allRestaurants")
    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRestaurants(@Context HttpServletResponse response) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            RestaurantService restaurantService = new RestaurantServiceImpl(con);
            response.setHeader("Access-Control-Allow-Origin", "*");
            String result = restaurantService.getAllRestaurants();
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
