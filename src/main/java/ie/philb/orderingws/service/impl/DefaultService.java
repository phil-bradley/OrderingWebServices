/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.CurrencyDao;
import ie.philb.orderingws.service.ServiceException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class DefaultService {

    private static final Logger logger = Logger.getLogger(DefaultService.class.getSimpleName());
    protected DataSource ds;
    
    public DefaultService() throws ServiceException {

        try {
            logger.info("Getting initial context");
            InitialContext cxt = new InitialContext();

            logger.info("Getting datasource");
            ds = (DataSource) cxt.lookup("jdbc/ordering");

            if (ds == null) {
                throw new ServiceException("Failed to initialise Service, DataSource not found");
            }

        } catch (NamingException nx) {
            throw new ServiceException("Failed to initialise Service", nx);
        }
    }
}
