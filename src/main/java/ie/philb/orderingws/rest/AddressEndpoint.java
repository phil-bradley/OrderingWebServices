/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.AddressService;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("addresses")
public class AddressEndpoint {

    private AddressService addressService;

    public AddressEndpoint() {
        try {
            addressService = new AddressService();
        } catch (ServiceException nx) {
            throw new RuntimeException("Failed to instantiate address service", nx);
        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Address> listAll() {

        try {
            return addressService.listAddresses();
        } catch (ServiceException sx) {
            return Collections.EMPTY_LIST;
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Address getById(@PathParam("id") Long id) {
        try {
            return addressService.getAddress(id);
        } catch (ServiceException sx) {
            return null;
        }
    }
}
