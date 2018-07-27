package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.DAO.LoginDAO;
import com.sfu.cmpt470.DAO.RestaurantDAO;
import com.sfu.cmpt470.Util.LoginException;
import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/auth")
public class LoginWebAPI {

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password){
        try {
            DatabaseConnector con = new DatabaseConnector();
            String token = new LoginDAO(con).login(username, StringUtil.SHA1Hash(password)).getToken();
            long restaurantId = new RestaurantDAO(con).findRestaurantIdByUsername(username);
            String restaurantByUsername = new RestaurantDAO(con).findRestaurantByUsername(username);
            con.disconnect();
            return Response.ok().entity("{\"token\":\""+ token +"\"" +
                    "," +
                    "\"restaurant_id\":\""+ restaurantId +"\"" +
                    "," +
                    "\"restaurant_name\":\""+ restaurantByUsername +"\"}").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}
