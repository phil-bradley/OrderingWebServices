/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.dao;

import ie.philb.orderingws.model.Country;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class CachingDao {

    private final CountryDao countryDao;
    private DataSource ds;
    private List<Country> countryList = null;

    public CachingDao(DataSource ds) {
        countryDao = new CountryDao(ds);
    }

    public Country getCountry(Long id) throws DaoException {
        if (countryList == null) {
            initCountries();
        }

        for (Country country : countryList) {
            if (Objects.equals(country.getId(), id)) {
                return country;
            }
        }

        return null;
    }

    public List<Country> getCountries() throws DaoException {

        if (countryList == null) {
            initCountries();
        }

        return countryList;
    }

    private synchronized void initCountries() throws DaoException {
        this.countryList = countryDao.list();
    }
}
