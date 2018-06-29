package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.service.OrderDetailServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orderDetail")
public class OrderDetailWebApi {
    @Path("/updateOrderDetail")
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrderDetailStatus(String orderDetail){
        try {
            DatabaseConnector con = new DatabaseConnector();
            OrderDetailServiceImpl service = new OrderDetailServiceImpl(con);
            String result = service.updateOrderDetail(orderDetail);
            con.disconnect();
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }
}
