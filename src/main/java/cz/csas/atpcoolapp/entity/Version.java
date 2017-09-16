package cz.csas.atpcoolapp.entity;

import java.util.Date;

/**
 * Created by Pavel
 *
 */
public class Version {

    private String name = "atpcoolapp";
    private String version = "1.0.1";
    private String buildDate = "20170910-1939";

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getBuildDate() {
        return buildDate;
    }

}
