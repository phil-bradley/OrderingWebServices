/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Product;
import java.util.List;

/**
 *
 * @author philb
 */
public interface IProductService {

    public List<Product> getProducts() throws ServiceException;

    public Product getProductById(Long id) throws ServiceException;

    public Product getProductBySkuCode(String skuCode) throws ServiceException;

}
