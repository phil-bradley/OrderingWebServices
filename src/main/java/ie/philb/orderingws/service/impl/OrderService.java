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
import java.util.Date;
import java.util.List;
import java.util.Queue;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jws.WebService;

@WebService(serviceName = "OrderService")
public class OrderService extends DefaultService {

    @Resource(mappedName = "jms/testQueueConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/testQueue")
    private Queue queue;

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
            if (order.getId() == null || 0 == order.getId()) {
                Long orderId = orderDao.createOrder(order.getBuyer().getId());
                order.setId(orderId);

            }

            for (OrderDetail detail : order.getDetails()) {

                if (detail.getId() == null || detail.getId() == 0) {
                    Long detailId = orderDao.createDetail();
                    detail.setId(detailId);
                }

                detail.setOrderId(order.getId());
                orderDao.updateDetail(detail);
            }

            Order updated = getOrder(order.getId());

            if (connectionFactory == null) {
                logger.info("Failed to create connection factory");
            } else {

                logger.info("Creating JMS context");
                JMSContext jmsContext = connectionFactory.createContext();
                logger.info("Done");

                if (jmsContext == null) {
                    logger.info("Failed to create JMS context");
                } else {
                    logger.info("Sending JMS message");
                    jmsContext.createProducer().send((Destination) queue, "Hello " + new Date().toGMTString());
                    logger.info("Sent JMS message");

                }
            }
            return updated;

        } catch (DaoException | NoSuchEntityDaoException dx) {
            throw new ServiceException("Failed to save order", dx);
        }
    }

}
