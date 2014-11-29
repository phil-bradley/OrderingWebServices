/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.CountryDao;
import ie.philb.orderingws.dao.CurrencyDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Author;
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.model.Currency;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.service.ServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "AuthorService")
public class AuthorService extends DefaultService {

    private final CountryDao countryDao;
    private final CurrencyDao currencyDao;

    public AuthorService() throws ServiceException {
        super();

        countryDao = new CountryDao(ds);
        currencyDao = new CurrencyDao(ds);
        logger.info("Created service");
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Fred", "Bloggs", "fred@bloggs.com"));
        authors.add(new Author("Joe", "Soap", "joe@soap.com"));

        return authors;
    }

    public List<Currency> getCurrencies() throws ServiceException {

        try {
            return currencyDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

    }

    public List<Country> getCountries() throws ServiceException {
//        List<Country> countries = new ArrayList<>();
//        countries.add(new Country(1L, "US", "United States of America", "USA"));
//
//        return countries;

        try {
            return countryDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

    }

    public List<Party> getParties() {
        List<Party> parties = new ArrayList<>();

        Party p = new Party();
        p.setId(1L);
        p.setName("Fred Inc");
        p.setEmail("info@fred.com");
        p.setWebsite("http://www.fred.com");
        p.setTelephone("+353 1 1234567");

        Address a = new Address();
        a.setStreet1("52");
        a.setStreet2("Main Street");
        a.setCity("Cavan");

        p.getAddresses().add(a);

        parties.add(p);
        return parties;
    }

}
