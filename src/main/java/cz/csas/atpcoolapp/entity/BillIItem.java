package cz.csas.atpcoolapp.entity;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class BillIItem {
    private String id;
    private String trnid;
    private String type;
    private String price;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillIItem(String id, String trnid, String type, String price, String name) {
        this.id = id;
        this.trnid = trnid;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public BillIItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrnid() {
        return trnid;
    }

    public void setTrnid(String trnid) {
        this.trnid = trnid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
