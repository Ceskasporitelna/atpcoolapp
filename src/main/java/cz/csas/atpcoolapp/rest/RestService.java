package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.entity.*;
import cz.csas.atpcoolapp.services.Record;
import cz.csas.atpcoolapp.services.StatPrices;
import cz.csas.atpcoolapp.services.TransactionService;
import org.jboss.resteasy.spi.touri.URITemplate;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;


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
        //new CSVReader().CSVRead("./012052-17data082417.csv");
        Map<String, Price> prices = new StatPrices().getStatPrices("0114301");
        Price price = prices.get("3018");
        System.out.println("price.getPrice() = " + price.getPrice());
        return Response.status(200).entity(version).build();
    }

    @Path("saifu")
    @POST
    @Produces("application/json")
    public Response saifu(String body) {
        try {
            System.out.println("Saifu: " + body);
            JSONObject jsonObject = new JSONObject(body);
            int amount = jsonObject.getInt("amount");
            String trnid = jsonObject.getString("trnId");

            Record record = new Record();

            record.writeTransaction(amount, trnid);
        } catch (JSONException e) {
            return Response.status(500).build();
        }

        return Response.status(204).build();
    }

    @Path("transactions")
    @GET
    @Produces("application/json")
    public Response transactions() {
        List<Transaction> transactions = TransactionService.getTransactions();
        return Response.status(200).entity(transactions).build();
    }

    @Path("transactions/{id}")
    @GET
    @Produces("application/json")
    public Response transactions(@PathParam("id") String id) {
        List<BillIItem> billItems = TransactionService.getBillItems(id);
        return Response.status(200).entity(billItems).build();
    }

    @Path("transactions/{id}/baskets")
    @GET
    @Produces("application/json")
    public Response baskets(@PathParam("id") String id) {
        List<Basket> billItems = TransactionService.getBaskets(id);
        return Response.status(200).entity(billItems).build();
    }


}
