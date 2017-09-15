package cz.csas.atpcoolapp.services;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.cryprography.paillier.PublicKey;
import cz.csas.atpcoolapp.db.Transactions;
import cz.csas.atpcoolapp.entity.Transaction;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Record {
    public static void writeTransaction(int amount, String trnid) {
        Transaction transaction = new Transaction();
        Transactions transDb = new Transactions();
        JSONObject pk = new JSONObject(Common.DEMO_PAILLIER_PUB);
        BigInteger nSquared = new BigInteger(pk.getString("nSquared"));
        BigInteger n = new BigInteger(pk.getString("n"));
        BigInteger g = new BigInteger(pk.getString("g"));
        int bits = 256;

        PublicKey publicKey = new PublicKey(n, nSquared, g, bits);



        transaction.setTrnid(trnid);
        try {
            transaction.setAmount(publicKey.encrypt(BigInteger.valueOf(amount)).toString());
        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
        }
        transaction.setCurrency("CZK");
        transaction.setExpdate("2030-01-01");
        transaction.setMpan("mpan");
        transaction.setStatus("status");
        transaction.setPubkey(Common.DEMO_PAILLIER_PUB);

        transDb.writeTransaction(transaction);
    }
}
