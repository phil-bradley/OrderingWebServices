/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.dao.OrderDao;
import ie.philb.orderingws.dao.PartyDao;
import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Order;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.service.ServiceException;
import static ie.philb.orderingws.service.impl.DefaultService.logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philb
 */
public class OrderService extends DefaultService {

    private final PartyDao partyDao;
    private final OrderDao orderDao;

    public OrderService() throws ServiceException {

        super();

        logger.info("Creating dao");
        partyDao = new PartyDao(ds);
        orderDao = new OrderDao(ds);
    }

    public Order getOrder(Long id) throws ServiceException {
        try {
            Order order = orderDao.get(id);
            Party buyer = partyDao.get(order.getBuyer().getId());
            order.setBuyer(buyer);

            return order;
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public List<Order> getOrdersByBuyer(Long buyerId) throws ServiceException {
        
        List<Order> orders = new ArrayList<>();
        
        try {
            Party buyer = partyDao.get(buyerId);
            orders = orderDao.getOrdersByBuyer(buyerId);

            for (Order order : orders) {
                order.setBuyer(buyer);
            }

        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);

        }
        
        return orders;
    }
}
