package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.entity.Version;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


/**
 * Created by Pavel
 *
 */
@Path("/")
public class RestService {
    @Path("version")
    @GET
    @Produces("application/json")
    public Response state() {
        Version version = new Version();

        return Response.status(200).entity(version).build();
    }
}
