/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.CurrencyDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.model.Currency;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "CurrencyService")
public class CurrencyService extends DefaultService {

    private final CurrencyDao currencyDao;

    public CurrencyService() throws ServiceException {

        super();

        logger.info("Creating dao");
        currencyDao = new CurrencyDao(ds);
    }

    @WebMethod
    public List<Currency> getCurrencies() throws ServiceException {

        try {
            return currencyDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

    }

    @WebMethod
    public Currency getCurrency(String code) throws ServiceException {
        try {
            return currencyDao.get(code);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
