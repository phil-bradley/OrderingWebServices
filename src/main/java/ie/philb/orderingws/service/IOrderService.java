/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Order;
import java.util.List;

/**
 *
 * @author philb
 */
public interface IOrderService {

    public Order getOrder(Long id) throws ServiceException;

    public List<Order> getOrdersByBuyer(Long buyerId) throws ServiceException;

    public Order saveOrder(Order order) throws ServiceException;
    
    public Order createOrder(Long buyerId) throws ServiceException;

}
