/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service.impl;

import ie.philb.orderingws.dao.DaoException;
import ie.philb.orderingws.dao.NoSuchEntityDaoException;
import ie.philb.orderingws.dao.ProductDao;
import ie.philb.orderingws.model.Product;
import ie.philb.orderingws.service.ServiceException;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "ProductService")
public class ProductService extends DefaultService {

    private final ProductDao productDao;

    public ProductService() throws ServiceException {

        super();

        logger.info("Creating dao");
        productDao = new ProductDao(ds);
    }

    public List<Product> getProducts() throws ServiceException {
        try {
            return productDao.list();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public Product getProductById(Long id) throws ServiceException {
        try {
            return productDao.get(id);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }

    public Product getProductBySkuCode(String skuCode) throws ServiceException {
        try {
            return productDao.getBySkuCode(skuCode);
        } catch (DaoException | NoSuchEntityDaoException ex) {
            throw new ServiceException(ex);
        }
    }
}
