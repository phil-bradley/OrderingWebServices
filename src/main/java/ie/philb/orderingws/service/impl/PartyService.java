/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.AddressDao;
import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.dao.PartyDao;
import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import java.util.logging.Logger;
import javax.jws.WebService;


@WebService(serviceName = "PartyService")
public class PartyService extends DefaultService  {

    private static final Logger logger = Logger.getLogger(PartyService.class.getSimpleName());
    private final PartyDao partyDao;
    private final AddressDao addressDao;

    public PartyService() throws ServiceException {

        super();

        logger.info("Creating dao");
        partyDao = new PartyDao(ds);
        addressDao = new AddressDao(ds);
    }

    public List<Party> list() throws ServiceException {

        try {
            List<Party> parties = partyDao.list();

            for (Party p : parties) {
                List<Address> addresses = addressDao.getByPartyId(p.getId());
                p.setAddresses(addresses);
            }
            
            return parties;
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public Party get(Long id) throws ServiceException {
        try {
            Party p = partyDao.get(id);

            List<Address> addresses = addressDao.getByPartyId(id);
            p.setAddresses(addresses);

            return p;
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

}
