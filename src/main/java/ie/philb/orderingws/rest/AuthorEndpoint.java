/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.rest;

import ie.philb.orderingws.model.Author;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("authors")
public class AuthorEndpoint {

    @GET    
    @Produces({"application/xml", "application/json"})
    public List<Author> listAll() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Fred", "Bloggs", "fred@bloggs.com"));
        authors.add(new Author("Joe", "Soap", "joe@soap.com"));

        return authors;
    }

}
