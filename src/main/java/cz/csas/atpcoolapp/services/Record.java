package cz.csas.atpcoolapp.services;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.db.Transactions;
import cz.csas.atpcoolapp.entity.Transaction;

import java.util.Date;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Record {
    public static void writeTransaction(int amount, String trnid) {
        Transaction transaction = new Transaction();
        Transactions transDb = new Transactions();

        transaction.setTrnid(trnid);
        transaction.setAmount("encrypt this");
        transaction.setCurrency("CZK");
        transaction.setExpdate((new Date()).toString());
        transaction.setMpan("mpan");
        transaction.setStatus("status");
        transaction.setPubkey(Common.DEMO_PAILLIER_PUB);

        transDb.writeTransaction(transaction);
    }
}
