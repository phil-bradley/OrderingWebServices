/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Currency;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.CurrencyService;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("currency")
public class CurrencyEndpoint {

    CurrencyService currencyService;

    public CurrencyEndpoint() {
        try {
            currencyService = new CurrencyService();
        } catch (ServiceException nx) {
            throw new RuntimeException("Failed to instantiate currency service", nx);
        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Currency> listAll() {

        try {
            return currencyService.list();
        } catch (ServiceException sx) {
            return Collections.EMPTY_LIST;
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Currency getById(@PathParam("id") Long id) {
        try {
            return currencyService.get(id);
        } catch (ServiceException sx) {
            return null;
        }
    }

}
