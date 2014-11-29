/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.CountryDao;
import ie.philb.orderingws.dao.CurrencyDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.model.Currency;
import ie.philb.orderingws.service.ICurrencyService;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Stateless
@WebService
public class CurrencyService extends DefaultService implements ICurrencyService {

    private static final Logger logger = Logger.getLogger(CurrencyService.class.getSimpleName());
    private final CurrencyDao currencyDao;

    public CurrencyService() throws ServiceException {

        super();

        logger.info("Creating dao");
        currencyDao = new CurrencyDao(ds);
    }

    @Override
    public List<Currency> list() throws ServiceException {
        try {
            return currencyDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Currency get(Long id) throws ServiceException {
        try {
            return currencyDao.get(id);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
