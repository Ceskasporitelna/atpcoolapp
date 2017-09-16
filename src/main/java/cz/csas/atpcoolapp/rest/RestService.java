package cz.csas.atpcoolapp.rest;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.cryprography.paillier.KeyPair;
import cz.csas.atpcoolapp.cryprography.paillier.PrivateKey;
import cz.csas.atpcoolapp.cryprography.paillier.PublicKey;
import cz.csas.atpcoolapp.entity.BillIItem;
import cz.csas.atpcoolapp.entity.Price;
import cz.csas.atpcoolapp.entity.Transaction;
import cz.csas.atpcoolapp.entity.Version;
import cz.csas.atpcoolapp.services.Record;
import cz.csas.atpcoolapp.services.StatPrices;
import cz.csas.atpcoolapp.services.TransactionService;
import org.jboss.resteasy.spi.touri.URITemplate;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
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


    @Path("decrypt")
    @GET
    @Produces("application/json")
    public Response decryption(@QueryParam("data") String data) {

        JSONObject res = new JSONObject();

        res.put("value", decrypt(new BigInteger(data)).toString());

        return Response.status(200).entity(res).build();
    }


    private BigInteger decrypt(BigInteger data) {
        JSONObject pk = new JSONObject(Common.DEMO_PAILLIER_PRIV);
        BigInteger lambda = new BigInteger(pk.getString("lambda"));
        BigInteger preCalculatedDenominator = new BigInteger(pk.getString("preCalculatedDenominator"));
        PrivateKey privateKey = new PrivateKey(lambda, preCalculatedDenominator);
        JSONObject pubk = new JSONObject(Common.DEMO_PAILLIER_PUB);
        BigInteger nSquared = new BigInteger(pubk.getString("nSquared"));
        BigInteger n = new BigInteger(pubk.getString("n"));
        BigInteger g = new BigInteger(pubk.getString("g"));
        int bits = 256;
        PublicKey publicKey = new PublicKey(n, nSquared, g, bits);

        KeyPair keyPair = new KeyPair(privateKey, publicKey, null);

        return keyPair.decrypt(data);
    }
}
