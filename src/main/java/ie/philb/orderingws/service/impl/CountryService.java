/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.CountryDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(serviceName = "CountryService")
public class CountryService extends DefaultService {

    private static final Logger logger = Logger.getLogger(CountryService.class.getSimpleName());
    private final CountryDao countryDao;

    public CountryService() throws ServiceException {

        super();
        
        logger.info("Creating dao");
        countryDao = new CountryDao(ds);
    }

    public List<Country> listCountries() throws ServiceException {
        try {
            return countryDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public Country getCountryById(Long id) throws ServiceException {
        try {
            return countryDao.get(id);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public int deleteCountry(Long id) throws ServiceException {
        try {
            return countryDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public long createCountry(Country country) throws ServiceException {
        try {
            return countryDao.create(country);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public int updateCountry(Country country) throws ServiceException {
        try {
            return countryDao.update(country);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
