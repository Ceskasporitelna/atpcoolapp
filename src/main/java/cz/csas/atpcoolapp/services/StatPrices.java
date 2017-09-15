package cz.csas.atpcoolapp.services;

import cz.csas.atpcoolapp.Common;
import cz.csas.atpcoolapp.entity.Price;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cen29253 on 15.9.2017.
 */
public class StatPrices {

    private static final String SELECT_PRICES = "select * from HPLATFORM.STATPRICES m, (select max(todate) as datum from HPLATFORM.STATPRICES where cenkod = ?) d where d.datum = m.todate and cenkod = ?";

    public static Map<String, Price> getStatPrices(String type) {
        Map<String, Price> statPrices = null;
        if (type != null) {
            Connection conn = null;
            PreparedStatement preparedStatement;
            Context ctx = null;
            String pool_data_source = Common.POOL_DATA_SOURCE;
            ResultSet rs = null;
            try {
                ctx = new InitialContext();
                javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(pool_data_source);
                conn = ds.getConnection();
                preparedStatement = conn.prepareStatement(SELECT_PRICES);
                preparedStatement.setString(1, type);
                preparedStatement.setString(2, type);

                rs = preparedStatement.executeQuery();

                if (rs != null) {
                    statPrices = new HashMap<String,Price>();
                    while (rs.next()) {
                        Price statPrice = new Price();
                        statPrice.setId(rs.getString("id"));
                        statPrice.setPrice(new Double(rs.getDouble("value")*100).intValue() + "");
                        statPrice.setRegion(rs.getString("regionkode"));
                        statPrice.setName(rs.getString("name"));
                        statPrice.setType(rs.getString("cenkod"));
                        statPrices.put(statPrice.getRegion(),statPrice);
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
        }

        return statPrices;
    }

}
