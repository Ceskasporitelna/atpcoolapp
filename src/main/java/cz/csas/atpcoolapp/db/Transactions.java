package cz.csas.atpcoolapp.db;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.entity.BillIItem;
import cz.csas.atpcoolapp.entity.Price;
import cz.csas.atpcoolapp.entity.Transaction;
import org.json.JSONObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Transactions {
    private static final String INSERT_TRANSACTION = "insert into HPLATFORM.TRANSACTIONS values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_BILLITEM = "insert into HPLATFORM.BILITEMS values (?, ?, ?, ?, ?)";
    private static final String INSERT_PRICE = "insert into HPLATFORM.PRICES values (?, ?, ?, ?, ?)";

    public void writeTransaction(Transaction transaction) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;

        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(INSERT_TRANSACTION);
            preparedStatement.setString(1, transaction.getTrnid());
            preparedStatement.setString(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getCurrency());
            preparedStatement.setString(4, transaction.getExpdate());
            preparedStatement.setString(5, transaction.getMpan());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
            preparedStatement.setString(7, transaction.getStatus());
            preparedStatement.setString(8, transaction.getPubkey());

            preparedStatement.execute();

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
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
    }

    public void writeBillItem(BillIItem billItem) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;

        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(INSERT_BILLITEM);
            preparedStatement.setString(1, billItem.getId());
            preparedStatement.setString(2, billItem.getTrnid());
            preparedStatement.setString(3, billItem.getType());
            preparedStatement.setString(4, billItem.getPrice());
            preparedStatement.setString(5, billItem.getName());


            preparedStatement.execute();

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
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
    }

    public void writePrices(List<Price> prices) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        Context ctx = null;
        String pool_data_source = Common.POOL_DATA_SOURCE;

        try {
            ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
            conn = ds.getConnection();
            preparedStatement = conn.prepareStatement(INSERT_PRICE);

            for(Price price: prices) {
                preparedStatement.setString(1, price.getId());
                preparedStatement.setString(2, price.getTrnid());
                preparedStatement.setString(3, price.getType());
                preparedStatement.setString(4, price.getRegion());
                preparedStatement.setString(5, price.getPrice());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
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
    }
}
