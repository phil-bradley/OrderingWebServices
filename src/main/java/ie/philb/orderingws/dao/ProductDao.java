/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.dao;

import ie.philb.orderingws.jdbc.EntityMapper;
import ie.philb.orderingws.jdbc.JdbcException;
import ie.philb.orderingws.jdbc.JdbcParameterSet;
import ie.philb.orderingws.jdbc.NoSuchEntityException;
import ie.philb.orderingws.jdbc.RowMapper;
import ie.philb.orderingws.model.Money;
import ie.philb.orderingws.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class ProductDao extends AbstractDao<Product> {

    private final ProductMapper productMapper = new ProductMapper();

    public ProductDao(DataSource ds) {
        super(ds);
    }

    @Override
    public Product get(Long id) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("id", id);

            Product product = getJdbcTemplate().querySingleResult("SELECT * FROM product WHERE id = :id", params, productMapper);
            return product;
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get product " + id), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get product " + id);
        }
    }

    public Product getBySkuCode(String skuCode) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("SKUCODE", skuCode);

            Product product = getJdbcTemplate().querySingleResult("SELECT * FROM product WHERE skucode = :SKUCODE", params, productMapper);
            return product;
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get product " + skuCode), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get product " + skuCode);
        }
    }

    @Override
    public long count() throws DaoException {
        try {
            return getJdbcTemplate().querySingleResult("SELECT count(*) FROM product", (ResultSet rs) -> rs.getLong(1));
        } catch (JdbcException | NoSuchEntityException jdx) {
            throw new DaoException("Failed to get product count", jdx);
        }
    }

    @Override
    public long create(Product t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product t) throws DaoException, NoSuchEntityDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = "DELETE FROM product WHERE id = :ID";

        JdbcParameterSet params = new JdbcParameterSet();
        params.add("id", id);

        try {
            return getJdbcTemplate().executeUpdate(sql, params);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to delete product", jdx);
        }
    }

    @Override
    public List<Product> list() throws DaoException {
        try {
            List<Product> products = getJdbcTemplate().queryResultList("SELECT * FROM product", productMapper);
            return products;
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get products", jdx);
        }
    }

    class ProductMapper implements RowMapper<Product>, EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) throws SQLException {
            Product product = new Product();
            product.setSkuCode(rs.getString("SKUCODE"));
            product.setTitle(rs.getString("TITLE"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setUnitPrice(new Money(rs.getBigDecimal("UNITPRICE")));

            return product;
        }

        @Override
        public JdbcParameterSet getParameterSet(Product product) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("SKUCODE", product.getSkuCode());
            parameters.add("TITLE", product.getTitle());
            parameters.add("DESCRIPTION", product.getDescription());
            parameters.add("UNITPRICE", product.getUnitPrice().asBigDecimal());

            return parameters;
        }
    }
}
