package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.service.CRUDMode;
import com.sfu.cmpt470.service.DishService;
import com.sfu.cmpt470.service.DishServiceImpl;
import jersey.repackaged.com.google.common.collect.ImmutableList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@SuppressWarnings("Duplicates")
@Path("/dish")
public class DishWebAPI {
    @Path("/getDishesByRestaurant")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getDishes(@Context HttpServletResponse response,
                            @QueryParam("restaurant_name") String restaurantName,
                            @QueryParam("restaurant_id") Long restaurantID) {
        if(restaurantID == null && StringUtil.isNullOrEmpty(restaurantName)){
            return "";
        }
        if(restaurantID != null){
            try {
                DatabaseConnector con = new DatabaseConnector();
                DishService dishService = new DishServiceImpl(con);
                String result = dishService.getDishesFor(restaurantID);
                con.disconnect();
                return result;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        else{
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
    }


    @Path("/getDishByID")
    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getDishByID(@Context HttpServletResponse response,
                           @QueryParam("dish_id") long dishID) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl dishService = new DishServiceImpl(con);
            String result = dishService.getDishByID(dishID);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/getDish")
    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getDish(@Context HttpServletResponse response,
                          @QueryParam("dishVerID") long dishVerID) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishService dishService = new DishServiceImpl(con);
            String result = dishService.getDish(dishVerID);
            con.disconnect();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/getDishes")
    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllDishes(@QueryParam("dish_ver_id")List<Long> dishVerIDs) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl service = new DishServiceImpl(con);
            String result = service.getDishesByDishVerID(dishVerIDs);
            con.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Path("/updateDish")
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateDish(String dish) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl service = new DishServiceImpl(con);
            String result = service.updateOrCreateDish(dish, CRUDMode.UPDATE);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }

    @Path("/createDish")
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createDish(String dish) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl service = new DishServiceImpl(con);
            String result = service.updateOrCreateDish(dish, CRUDMode.CREATE);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }
    @Path("/deleteDish")
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteDish(String dish) {
        try {
            DatabaseConnector con = new DatabaseConnector();
            DishServiceImpl service = new DishServiceImpl(con);
            String result = service.updateOrCreateDish(dish, CRUDMode.DELETE);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }


}
