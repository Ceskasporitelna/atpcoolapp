package cz.csas.atpcoolapp.entity;

/**
 * Created by pavel on 15.09.2017.
 *
 */
public class Price {
    private String id;
    private String trnid;
    private String type;
    private String region;
    private String price;
    private String name;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
