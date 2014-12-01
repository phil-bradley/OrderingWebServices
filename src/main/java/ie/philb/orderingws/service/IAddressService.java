/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Address;
import java.util.List;

/**
 *
 * @author philb
 */
public interface IAddressService {

    public List<Address> listAddresses() throws ServiceException;

    public Address getAddress(Long id) throws ServiceException;

    public int deleteAddress(Long id) throws ServiceException;

    public long createAddress(Address address) throws ServiceException;

    public int updateAddress(Address address) throws ServiceException;
}
