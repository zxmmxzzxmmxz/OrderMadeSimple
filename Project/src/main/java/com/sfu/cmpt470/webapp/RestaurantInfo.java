package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.service.RestInfoService;
import com.sfu.cmpt470.service.RestInfoServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/restaurant")
public class RestaurantInfo {
    //option1: rest/info?restaurant_id={restId}
    @Path("/info/{restaurant_name}")
    @GET

    @Produces(MediaType.APPLICATION_JSON)
    public Response openInfo(@PathParam("restaurant_name") String name){
        try{
            RestInfoService ri = new RestInfoServiceImpl();
            String result = ri.getAllInformation(name);
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
                //
                RestInfoService ris = new RestInfoServiceImpl();
                boolean res = ris.storeReservation(reservation);
                if(res){
                    return Response.ok("Your reservation is booked.").build();
                }else{
                    return Response.status(400).entity("Server can not post request").build();
                }
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

    @Path("/{time}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTimes(@PathParam("time") String time){
        try{
            String result = new RestInfoServiceImpl().getSeats(time);
            return Response.ok().entity(result).build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(400).entity("Server can not post request").build();
        }
    }


}
