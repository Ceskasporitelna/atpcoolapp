package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.entity.Version;
import cz.csas.atpcoolapp.services.Record;
import org.json.JSONException;
import org.json.JSONObject;

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
        try {
            JSONObject jsonObject = new JSONObject(body);
            int amount = jsonObject.getInt("amount");
            String trnid = jsonObject.getString("trnId");

            Record.writeTransaction(amount, trnid);
        } catch (JSONException e) {
            return Response.status(500).build();
        }

        return Response.status(204).build();
    }
}
