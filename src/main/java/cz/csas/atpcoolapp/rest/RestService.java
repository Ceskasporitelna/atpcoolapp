package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.entity.Price;
import cz.csas.atpcoolapp.entity.Transaction;
import cz.csas.atpcoolapp.entity.Version;
import cz.csas.atpcoolapp.services.StatPrices;
import cz.csas.atpcoolapp.services.TransactionService;
import cz.csas.atpcoolapp.utils.CSVReader;

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

        //TODO: create "real" data

        return Response.status(204).build();
    }

    @Path("transactions")
    @GET
    @Produces("application/json")
    public Response transactions() {
        List<Transaction> transactions = TransactionService.getTransactions();
        return Response.status(200).entity(transactions).build();
    }

}
