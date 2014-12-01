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
import ie.philb.orderingws.model.Order;
import ie.philb.orderingws.model.OrderDetail;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.service.ServiceException;
import static ie.philb.orderingws.service.impl.DefaultService.logger;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "OrderService")
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

    public Order createOrder(Long buyerId) throws ServiceException {
        try {
            Long orderId = orderDao.createOrder(buyerId);
            Order order = orderDao.getHeader(orderId);
            return order;
        } catch (DaoException | NoSuchEntityDaoException dx) {
            throw new ServiceException("Failed to create order", dx);
        }
    }

    public Order saveOrder(Order order) throws ServiceException {

        try {
            if (order.getId() == 0) {
                Long orderId = orderDao.createOrder(order.getBuyer().getId());
                order.setId(orderId);

            }

            for (OrderDetail detail : order.getDetail()) {
                if (detail.getId() == 0) {
                    Long detailId = orderDao.createDetail();
                    detail.setId(detailId);
                } else {
                    orderDao.updateDetail(detail);
                }
            }

            return order;

        } catch (DaoException | NoSuchEntityDaoException dx) {
            throw new ServiceException("Failed to save order", dx);
        }

    }

}
