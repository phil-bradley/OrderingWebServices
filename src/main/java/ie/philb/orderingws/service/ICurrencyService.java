/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Currency;
import java.util.List;

/**
 *
 * @author philb
 */
public interface ICurrencyService {

    public List<Currency> list() throws ServiceException;

    public Currency get(Long id) throws ServiceException;
}
