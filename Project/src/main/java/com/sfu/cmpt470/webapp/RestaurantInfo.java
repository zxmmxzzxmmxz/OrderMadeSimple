package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.RestInfoService;
import com.sfu.cmpt470.service.RestInfoServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 7/17/2018.
 */

@Path("/info/{restaurant_id}")
public class RestaurantInfo {
    //option1: rest/info?restaurant_id={restId}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response openInfo(@PathParam("restaurant_id") int restaurantID){
        try{
            RestInfoService ri = new RestInfoServiceImpl();
            String result = ri.getAllInformation(restaurantID);
            return Response.ok().entity(result).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(401).entity("Server can not process the request").build();
        }
    }

}
