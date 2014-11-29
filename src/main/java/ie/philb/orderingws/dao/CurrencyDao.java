/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.dao;

import ie.philb.orderingws.jdbc.EntityMapper;
import ie.philb.orderingws.jdbc.JdbcException;
import ie.philb.orderingws.jdbc.JdbcParameterSet;
import ie.philb.orderingws.jdbc.JdbcTemplate;
import ie.philb.orderingws.jdbc.NoSuchEntityException;
import ie.philb.orderingws.jdbc.RowMapper;
import ie.philb.orderingws.model.Country;
import ie.philb.orderingws.model.Currency;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import lombok.NonNull;

public class CurrencyDao {

    private final CurrencyMapper currencyMapper = new CurrencyMapper();
    private final DataSource ds;
    private final JdbcTemplate jdbcTemplate;

    public CurrencyDao(@NonNull DataSource ds) {
        this.ds = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public Currency get(String code) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("CODE", code);

            return getJdbcTemplate().querySingleResult("SELECT * FROM currency WHERE code = :CODE", params, currencyMapper);
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get currency " + code), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get currency " + code);
        }
    }

    public long count() throws DaoException {
        try {
            return getJdbcTemplate().querySingleResult("SELECT count(*) FROM country", (ResultSet rs) -> rs.getLong(1));
        } catch (JdbcException | NoSuchEntityException jdx) {
            throw new DaoException("Failed to get currency count", jdx);
        }
    }

    public List<Currency> list() throws DaoException {
        try {
            return getJdbcTemplate().queryResultList("SELECT * FROM currency", currencyMapper);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get currencies", jdx);
        }
    }

    public long create(Currency t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int update(Currency t) throws DaoException, NoSuchEntityDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int delete(Long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
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
