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

    public List<Country> listCountries() throws ServiceException;

    public Country getCountryById(Long id) throws ServiceException;

    public int deleteCountry(Long id) throws ServiceException;

    public long createCountry(Country country) throws ServiceException;

    public int updateCountry(Country country) throws ServiceException;

}
