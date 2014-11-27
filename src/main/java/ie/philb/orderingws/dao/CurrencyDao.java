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
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.model.Currency;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

public class CurrencyDao extends AbstractDao<Currency> {

    private final CurrencyMapper currencyMapper = new CurrencyMapper();

    public CurrencyDao(DataSource ds) {
        super(ds);
    }

    @Override
    public Currency get(Long id) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("id", id);

            return getJdbcTemplate().querySingleResult("SELECT * FROM currency WHERE id = :id", params, currencyMapper);
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get currency " + id), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get currency " + id);
        }
    }

    @Override
    public long count() throws DaoException {
        try {
            return getJdbcTemplate().querySingleResult("SELECT count(*) FROM country", (ResultSet rs) -> rs.getLong(1));
        } catch (JdbcException | NoSuchEntityException jdx) {
            throw new DaoException("Failed to get currency count", jdx);
        }
    }

    @Override
    public List<Currency> list() throws DaoException {
        try {
            return getJdbcTemplate().queryResultList("SELECT * FROM currency", currencyMapper);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get currencies", jdx);
        }
    }

    @Override
    public long create(Currency t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Currency t) throws DaoException, NoSuchEntityDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class CurrencyMapper implements RowMapper<Currency>, EntityMapper<Currency> {

        @Override
        public Currency mapRow(ResultSet rs) throws SQLException {
            Currency c = new Currency();
            c.setCode(rs.getString("CODE"));
            c.setName(rs.getString("NAME"));

            return c;
        }

        @Override
        public JdbcParameterSet getParameterSet(Currency currency) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("CODE", currency.getCode());
            parameters.add("NAME", currency.getName());

            return parameters;
        }

    }

}
