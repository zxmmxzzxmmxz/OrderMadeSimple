package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.DAO.LoginDAO;
import com.sfu.cmpt470.DAO.RestaurantDAO;
import com.sfu.cmpt470.Util.LoginException;
import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.annotation.Secured;

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
            return Response.ok().entity("{\"token\":\""+new LoginDAO().login(username, StringUtil.SHA1Hash(password)).getToken()+"\"" +
                    "," +
                    "\"restaurant_name\":\""+new RestaurantDAO().findRestaurantByUsername(username)+"\"}").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}
