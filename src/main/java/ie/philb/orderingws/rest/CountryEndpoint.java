/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.CountryService;
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("countries")
public class CountryEndpoint {

    CountryService countryService;

    public CountryEndpoint() {
        try {
            countryService = new CountryService();
        } catch (ServiceException nx) {
            throw new RuntimeException("Failed to instantiate country service", nx);
        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Country> listAll() {

        try {
            return countryService.list();
        } catch (ServiceException sx) {
            return Collections.EMPTY_LIST;
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Country getById(@PathParam("id") Long id) {
        try {
            return countryService.get(id);
        } catch (ServiceException sx) {
            return null;
        }
    }
}
