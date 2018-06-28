package com.sfu.cmpt470.webapp;

import com.sfu.cmpt470.annotation.Secured;
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
            OrderDetailServiceImpl service = new OrderDetailServiceImpl();
            return Response.ok().entity(service.updateOrderDetail(orderDetail)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server can not process the request").build();
        }
    }
}
