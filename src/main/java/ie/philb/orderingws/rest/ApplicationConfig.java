/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /*
     * This should be populated with all resource classes 
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ie.philb.orderingws.rest.AddressEndpoint.class);
        resources.add(ie.philb.orderingws.rest.AuthorEndpoint.class);
        resources.add(ie.philb.orderingws.rest.CountryEndpoint.class);
        resources.add(ie.philb.orderingws.rest.CurrencyEndpoint.class);
    }
}
