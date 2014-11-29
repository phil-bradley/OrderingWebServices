/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.AddressDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(serviceName = "AddressService")
public class AddressService extends DefaultService {

    private static final Logger logger = Logger.getLogger(AddressService.class.getSimpleName());
    private final AddressDao addressDao;

    public AddressService() throws ServiceException {

        super();

        logger.info("Creating dao");
        addressDao = new AddressDao(ds);
    }

    public List<Address> listAddresses() throws ServiceException {
        try {
            return addressDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public Address getAddress(Long id) throws ServiceException {
        try {
            return addressDao.get(id);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public int deleteAddress(Long id) throws ServiceException {
        try {
            return addressDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public long createAddress(Address country) throws ServiceException {
        try {
            return addressDao.create(country);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public int updateAddress(Address country) throws ServiceException {
        try {
            return addressDao.update(country);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
