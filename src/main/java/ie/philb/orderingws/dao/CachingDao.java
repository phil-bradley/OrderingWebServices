/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.dao;

import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.model.Currency;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class CachingDao {

    private final CountryDao countryDao;
    private final CurrencyDao currencyDao;
    private List<Country> countryList = null;
    private List<Currency> currencyList = null;

    public CachingDao(DataSource ds) {
        countryDao = new CountryDao(ds);
        currencyDao = new CurrencyDao(ds);
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

    public Currency getCurrency(String code) throws DaoException {

        if (currencyList == null) {
            initCurrencies();
        }

        for (Currency c : currencyList) {
            if (Objects.equals(c.getCode(), code)) {
                return c;
            }
        }

        return null;
    }

    public List<Currency> getCurrencies() throws DaoException {

        if (currencyList == null) {
            initCurrencies();
        }

        return currencyList;
    }

    private synchronized void initCountries() throws DaoException {
        this.countryList = countryDao.list();
    }

    private synchronized void initCurrencies() throws DaoException {
        this.currencyList = currencyDao.list();
    }
}
