package cz.csas.atpcoolapp.entity;

/**
 * Created by cen29253 on 15.9.2017.
 */
public class TicketItem {
    String id;
    String name;
    String price;

    public TicketItem(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
