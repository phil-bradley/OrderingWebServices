/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Country;
import java.util.List;

/**
 *
 * @author philb
 */
public interface ICountryService {

    public List<Country> list() throws ServiceException;

    public Country get(Long id) throws ServiceException;

    public int delete(Long id) throws ServiceException;

    public long create(Country country) throws ServiceException;

    public int update(Country country) throws ServiceException;

}
