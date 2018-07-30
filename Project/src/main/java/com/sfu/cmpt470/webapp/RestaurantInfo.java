package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.RestInfoService;
import com.sfu.cmpt470.service.RestInfoServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/restaurant")
public class RestaurantInfo {
    //option1: rest/info?restaurant_id={restId}
    @Path("/info/{restaurant_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response openInfo(@PathParam("restaurant_id") int restaurantID){
        try{
            RestInfoService ri = new RestInfoServiceImpl();
            String result = ri.getAllInformation(restaurantID);
            return Response.ok().entity(result).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(401).entity("Server can not process request").build();
        }
    }

    @Path("/reserve")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response reserveTable(String reservation){
        try{
            if(reservation != null){
                System.out.println(reservation);
                return Response.ok("Your reservation is booked.").build();
            }else{
                throw new NullPointerException();
            }


        }catch (NullPointerException nex){
            return Response.status(400).entity("JSON cannot be empty").build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(400).entity("Server can not post request").build();
        }
    }

}
