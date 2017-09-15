package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.entity.Version;
import cz.csas.atpcoolapp.services.Record;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;


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

    @Path("saifu")
    @POST
    @Produces("application/json")
    public Response saifu(String body) {

        //TODO: create "real" data
        UUID trnid = UUID.randomUUID();

        Record.writeTransaction(100, trnid.toString());

        return Response.status(204).build();
    }
}
