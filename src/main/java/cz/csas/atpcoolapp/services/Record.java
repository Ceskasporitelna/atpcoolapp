package cz.csas.atpcoolapp.services;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.cryprography.paillier.PublicKey;
import cz.csas.atpcoolapp.db.Transactions;
import cz.csas.atpcoolapp.entity.BillIItem;
import cz.csas.atpcoolapp.entity.Price;
import cz.csas.atpcoolapp.entity.Transaction;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Record {
    public void writeTransaction(int amount, String trnid) {
        Transaction transaction = new Transaction();
        Transactions transDb = new Transactions();
        JSONObject pk = new JSONObject(Common.DEMO_PAILLIER_PUB);
        BigInteger nSquared = new BigInteger(pk.getString("nSquared"));
        BigInteger n = new BigInteger(pk.getString("n"));
        BigInteger g = new BigInteger(pk.getString("g"));
        int bits = 256;

        PublicKey publicKey = new PublicKey(n, nSquared, g, bits);

        Common common = new Common();

        List<BillIItem> billItems = new ArrayList<BillIItem>();

        try {
            // --- PIVO
            BillIItem pivo = common.PIVO;
            pivo.setTrnid(trnid);
            pivo.setId(UUID.randomUUID().toString());
            pivo.setPrice(publicKey.encrypt(BigInteger.valueOf(2290)).toString());

            System.out.println("PIVO");

            transDb.writeBillItem(pivo);
            billItems.add(pivo);

            System.out.println("PIVO ZAPSANO");

        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
            System.out.println("PIVO CHYBA");
        }

        try {
            // --- MLEKO
            BillIItem mleko = common.MLEKO;
            mleko.setTrnid(trnid);
            mleko.setId(UUID.randomUUID().toString());
            mleko.setPrice(publicKey.encrypt(BigInteger.valueOf(1190)).toString());

            transDb.writeBillItem(mleko);
            billItems.add(mleko);

        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
        }

        try {
            // --- VINO
            BillIItem vino = common.VINO;
            vino.setTrnid(trnid);
            vino.setId(UUID.randomUUID().toString());
            vino.setPrice(publicKey.encrypt(BigInteger.valueOf(8500)).toString());

            transDb.writeBillItem(vino);
            billItems.add(vino);
        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
        }

        try {
            // --- VEJCE
            BillIItem vejce = common.VEJCE;
            vejce.setTrnid(trnid);
            vejce.setId(UUID.randomUUID().toString());
            vejce.setPrice(publicKey.encrypt(BigInteger.valueOf(3490)).toString());

            transDb.writeBillItem(vejce);
            billItems.add(vejce);
        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
        }

        List<Price> pricesList = new ArrayList<>();

        for(BillIItem billIItem: billItems) {
            Map<String, Price> pricesMap = StatPrices.getStatPrices(billIItem.getType());

            Set<String> regions = pricesMap.keySet();

            for(String region: regions) {
                Price price = pricesMap.get(region);
                price.setTrnid(trnid);
                try {
                    price.setPrice(publicKey.encrypt(new BigInteger(price.getPrice())).toString());
                } catch (PublicKey.InvalidPaillierValueException e) {
                    e.printStackTrace();
                }
                pricesList.add(price);
            }

        }

        transDb.writePrices(pricesList);

        transaction.setTrnid(trnid);
        try {
            transaction.setAmount(publicKey.encrypt(BigInteger.valueOf(amount)).toString());
        } catch (PublicKey.InvalidPaillierValueException e) {
            e.printStackTrace();
        }
        transaction.setCurrency("CZK");
        transaction.setExpdate("2030-01-01");
        transaction.setMpan("445566xxxxxx7890");
        transaction.setStatus("schvaleno");
        transaction.setPubkey(Common.DEMO_PAILLIER_PUB);

        transDb.writeTransaction(transaction);
    }
}
