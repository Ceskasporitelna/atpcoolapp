package cz.csas.atpcoolapp.entity;

import java.util.Date;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Transaction {
    private String trnid;
    private String amount;
    private String currency;
    private String expdate;
    private String mpan;
    private Date authdate;
    private String status;
    private String pubkey;

    public String getTrnid() {
        return trnid;
    }

    public void setTrnid(String trnid) {
        this.trnid = trnid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getMpan() {
        return mpan;
    }

    public void setMpan(String mpan) {
        this.mpan = mpan;
    }

    public Date getAuthdate() {
        return authdate;
    }

    public void setAuthdate(Date authdate) {
        this.authdate = authdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }
}
