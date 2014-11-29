/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.PartyService;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("parties")
public class PartyEndpoint {

    private PartyService partyService;

    public PartyEndpoint() {
        try {
            partyService = new PartyService();
        } catch (ServiceException nx) {
            throw new RuntimeException("Failed to instantiate party service", nx);
        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Party> listAll() {

        try {
            return partyService.list();
        } catch (ServiceException sx) {
            return Collections.EMPTY_LIST;
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Party getById(@PathParam("id") Long id) {
        try {
            return partyService.get(id);
        } catch (ServiceException sx) {
            return null;
        }
    }
}
