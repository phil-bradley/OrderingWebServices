/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Product;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.ProductService;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("products")
public class ProductEndpoint {

    private ProductService productService;

    public ProductEndpoint() {
        try {
            productService = new ProductService();
        } catch (ServiceException sx) {
            throw new RuntimeException("Failed to instantiate product service", sx);

        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Product> listAll() {

        try {
            return productService.getProducts();
        } catch (ServiceException sx) {
            return Collections.EMPTY_LIST;
        }
    }

    @GET
    @Path("{skucode}")
    @Produces({"application/xml", "application/json"})
    public Product getBySkuCode(@PathParam("skucode") String skucode) {
        try {
            return productService.getProductBySkuCode(skucode);
        } catch (ServiceException sx) {
            return null;
        }
    }

}
