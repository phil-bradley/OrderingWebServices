/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.soap;

import ie.philb.orderingws.model.Address;
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.model.Currency;
import ie.philb.orderingws.model.Order;
import ie.philb.orderingws.model.Party;
import ie.philb.orderingws.model.Product;
import ie.philb.orderingws.service.IAddressService;
import ie.philb.orderingws.service.ICountryService;
import ie.philb.orderingws.service.ICurrencyService;
import ie.philb.orderingws.service.IOrderService;
import ie.philb.orderingws.service.IPartyService;
import ie.philb.orderingws.service.IProductService;
import ie.philb.orderingws.service.ServiceException;
import ie.philb.orderingws.service.impl.AddressService;
import ie.philb.orderingws.service.impl.CountryService;
import ie.philb.orderingws.service.impl.CurrencyService;
import ie.philb.orderingws.service.impl.OrderService;
import ie.philb.orderingws.service.impl.PartyService;
import ie.philb.orderingws.service.impl.ProductService;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "OrderingAppService")
public class OrderingAppService implements 
        IAddressService, 
        ICountryService, 
        ICurrencyService, 
        IPartyService, 
        IProductService, 
        IOrderService {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CurrencyService currencyService;
    private final PartyService partyService;
    private final ProductService productService;
    private final OrderService orderService;

    public OrderingAppService() throws ServiceException {

        addressService = new AddressService();
        countryService = new CountryService();
        currencyService = new CurrencyService();
        partyService = new PartyService();
        productService = new ProductService();
        orderService = new OrderService();
    }

    @Override
    public List<Address> listAddresses() throws ServiceException {
        return addressService.listAddresses();
    }

    @Override
    public Address getAddress(Long id) throws ServiceException {
        return addressService.getAddress(id);
    }

    @Override
    public int deleteAddress(Long id) throws ServiceException {
        return addressService.deleteAddress(id);
    }

    @Override
    public long createAddress(Address address) throws ServiceException {
        return addressService.createAddress(address);
    }

    @Override
    public int updateAddress(Address address) throws ServiceException {
        return addressService.updateAddress(address);
    }

    @Override
    public List<Country> listCountries() throws ServiceException {
        return countryService.listCountries();
    }

    @Override
    public Country getCountryById(Long id) throws ServiceException {
        return countryService.getCountryById(id);
    }

    @Override
    public int deleteCountry(Long id) throws ServiceException {
        return countryService.deleteCountry(id);
    }

    @Override
    public long createCountry(Country country) throws ServiceException {
        return countryService.createCountry(country);
    }

    @Override
    public int updateCountry(Country country) throws ServiceException {
        return countryService.updateCountry(country);
    }

    @Override
    public List<Currency> getCurrencies() throws ServiceException {
        return currencyService.getCurrencies();
    }

    @Override
    public Currency getCurrency(String code) throws ServiceException {
        return currencyService.getCurrency(code);
    }

    @Override
    public List<Party> getParties() throws ServiceException {
        return partyService.getParties();
    }

    @Override
    public Party getParty(Long id) throws ServiceException {
        return partyService.getParty(id);
    }

    @Override
    public List<Product> getProducts() throws ServiceException {
        return productService.getProducts();
    }

    @Override
    public Product getProductById(Long id) throws ServiceException {
        return productService.getProductById(id);
    }

    @Override
    public Product getProductBySkuCode(String skuCode) throws ServiceException {
        return productService.getProductBySkuCode(skuCode);
    }

    @Override
    public Order getOrder(Long id) throws ServiceException {
        return orderService.getOrder(id);
    }

    @Override
    public List<Order> getOrdersByBuyer(Long buyerId) throws ServiceException {
        return orderService.getOrdersByBuyer(buyerId);
    }

    @Override
    public Order save(Order order) throws ServiceException {
        return orderService.save(order);
    }

}
