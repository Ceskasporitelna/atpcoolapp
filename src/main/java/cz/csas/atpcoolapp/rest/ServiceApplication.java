package cz.csas.atpcoolapp.rest;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration object for REST Service API
 *
 * Here can be various RESTeasy services registered
 *
 *
 */
public class ServiceApplication extends Application {

    HashSet<Object> singletons = new HashSet<Object>();

    public ServiceApplication() {

        // Services
        singletons.add(new RestService());

        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
        providerFactory.registerProvider(JacksonConfig.class);

    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}