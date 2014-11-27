/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

/**
 *
 * @author philb
 */
public class ServiceException extends Exception {
    
    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
    
    public ServiceException(Throwable t) {
        super(t);
    }
    
    public ServiceException(String message) {
        super(message);
    }
}
