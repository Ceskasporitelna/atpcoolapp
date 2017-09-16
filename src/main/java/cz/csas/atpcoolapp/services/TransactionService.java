package cz.csas.atpcoolapp.services;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.cryprography.paillier.PublicKey;
import cz.csas.atpcoolapp.entity.Basket;
import cz.csas.atpcoolapp.entity.BillIItem;
import cz.csas.atpcoolapp.entity.Price;
import cz.csas.atpcoolapp.entity.Transaction;
import org.json.JSONObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by cen29253 on 16.9.2017.
 */
public class TransactionService {
    private static final String SELECT_ALL_TRANSACTIONS = "select * from HPLATFORM.TRANSACTIONS order by authdate desc";
    private static final String SELECT_ITEMS_FOR_TRN = "select * from HPLATFORM.BILITEMS where trnid = ?";
    private static final String SELECT_PRICES_FOR_TRN = "select * from HPLATFORM.PRICES where trnid = ?";

    public static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_ALL_TRANSACTIONS);

            rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Transaction trn = new Transaction();
                    trn.setTrnid(rs.getString("trnid"));
                    trn.setAmount(rs.getString("amount"));
                    trn.setCurrency(rs.getString("currency"));
                    trn.setExpdate(rs.getString("expdate"));
                    trn.setMpan(rs.getString("mpan"));
                    trn.setStatus(rs.getString("status"));
                    trn.setAuthdate(rs.getTimestamp("authdate"));
                    //trn.setPubkey(rs.getString("pubkey"));
                    transactions.add(trn);
                }
            }

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (conn != null) {
                    conn.close();
                }

                if (ctx != null) {
                    ctx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }

    public static List<BillIItem> getBillItems(String trnid) {
        List<BillIItem> bills = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_ITEMS_FOR_TRN);
            preparedStatement.setString(1,trnid);

            rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    BillIItem bil = new BillIItem();
                    bil.setTrnid(rs.getString("trnid"));
                    bil.setId(rs.getString("id"));
                    bil.setName(rs.getString("name"));
                    bil.setPrice(rs.getString("price"));
                    bil.setType(rs.getString("type"));

                    bills.add(bil);
                }
            }

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (conn != null) {
                    conn.close();
                }

                if (ctx != null) {
                    ctx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return bills;
    }

    public static List<Basket> getBaskets(String trnid) {
        JSONObject pk = new JSONObject(Common.DEMO_PAILLIER_PUB);
        BigInteger nSquared = new BigInteger(pk.getString("nSquared"));
        BigInteger n = new BigInteger(pk.getString("n"));
        BigInteger g = new BigInteger(pk.getString("g"));
        int bits = 256;

        PublicKey publicKey = new PublicKey(n, nSquared, g, bits);

        List<Basket> baskets = new ArrayList<>();
        List<BillIItem> billIItems = getBillItems(trnid);
        BigInteger encryptedAddition = null;
        int idx = 0;
        for (BillIItem billIItem: billIItems) {
            if (idx == 0) {
                encryptedAddition = new BigInteger(billIItem.getPrice());
            } else {
                encryptedAddition = encryptedAddition.multiply(new BigInteger(billIItem.getPrice())).mod(publicKey.getnSquared());
            }
            idx++;
        }
        Basket basket = new Basket();
        basket.setName("Main");
        basket.setPrice(encryptedAddition.toString());

        baskets.add(basket);

        Map<String, BigInteger> otherBaskets = new HashMap<String, BigInteger>();

        List<Price> prices = getPrices(trnid);

        for (Price price:prices) {
            if (otherBaskets.get(price.getRegion()) == null) {
                otherBaskets.put(price.getRegion(), new BigInteger(price.getPrice()));
            } else {
                otherBaskets.put(price.getRegion(), otherBaskets.get(price.getRegion()).multiply(new BigInteger(price.getPrice())).mod(publicKey.getnSquared()));
            }
        }

        Set<String> keys = otherBaskets.keySet();
        for (String key:keys) {
            Basket basketS = new Basket();
            BigInteger price = otherBaskets.get(key);
            basketS.setPrice(price.toString());
            if (Common.regionMap.get(key) != null) {
                basketS.setName(Common.regionMap.get(key));
                baskets.add(basketS);
            }
        }

        return baskets;
    }


    public static List<Price> getPrices(String trnid) {
        List<Price> prices = new ArrayList();

        Connection conn = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_PRICES_FOR_TRN);
            preparedStatement.setString(1,trnid);

            rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Price price = new Price();
                    price.setId(rs.getString("id"));
                    price.setId(rs.getString("trnid"));
                    price.setRegion(rs.getString("region"));
                    price.setPrice(rs.getString("price"));
                    price.setType(rs.getString("type"));

                    prices.add(price);
                }
            }

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (conn != null) {
                    conn.close();
                }

                if (ctx != null) {
                    ctx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return prices;
    }

}
