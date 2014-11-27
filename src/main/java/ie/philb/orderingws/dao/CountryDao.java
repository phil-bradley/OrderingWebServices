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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class CountryDao extends AbstractDao<Country> {

    private final CountryMapper countryMapper = new CountryMapper();

    public CountryDao(DataSource ds) {
        super(ds);
    }

    @Override
    public Country get(Long id) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("id", id);

            return getJdbcTemplate().querySingleResult("SELECT * FROM country WHERE id = :id", params, countryMapper);
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get country " + id), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get country " + id);
        }
    }

    @Override
    public long count() throws DaoException {
        try {
            return getJdbcTemplate().querySingleResult("SELECT count(*) FROM country", (ResultSet rs) -> rs.getLong(1));
        } catch (JdbcException | NoSuchEntityException jdx) {
            throw new DaoException("Failed to get country count", jdx);
        }
    }

    @Override
    public long create(Country country) throws DaoException {

        String sql = "INSERT INTO country (ISO3, ISOCODE, NAME) ";
        sql += " VALUES (:ISO3, :ISOCODE, :NAME) ";

        try {
            return getJdbcTemplate().executeInsert(sql, countryMapper.getParameterSet(country));
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to insert country", jdx);
        }
    }

    @Override
    public int update(Country country) throws DaoException, NoSuchEntityDaoException {

        String sql = "UPDATE country SET ";
        sql += "ISO3 = :ISO3, ";
        sql += "ISOCODE = :ISOCODE, ";
        sql += "NAME = :NAME ";
        sql += "WHERE ID = :ID ";

        try {
            return getJdbcTemplate().executeUpdate(sql, countryMapper.getParameterSet(country));
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to update country count", jdx);
        }
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = "DELETE FROM country WHERE id = :ID";

        JdbcParameterSet params = new JdbcParameterSet();
        params.add("id", id);

        try {
            return getJdbcTemplate().executeUpdate(sql, params);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to delete country count", jdx);
        }
    }

    @Override
    public List<Country> list() throws DaoException {
        try {
            return getJdbcTemplate().queryResultList("SELECT * FROM country", countryMapper);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get countries", jdx);
        }
    }

    class CountryMapper implements RowMapper<Country>, EntityMapper<Country> {

        @Override
        public Country mapRow(ResultSet rs) throws SQLException {
            Country country = new Country();
            country.setId(rs.getLong("ID"));
            country.setIso3(rs.getString("ISO3"));
            country.setIsoCode(rs.getString("ISOCODE"));
            country.setName(rs.getString("NAME"));

            return country;
        }

        @Override
        public JdbcParameterSet getParameterSet(Country country) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("ID", country.getId());
            parameters.add("ISO3", country.getIso3());
            parameters.add("ISOCODE", country.getIsoCode());
            parameters.add("NAME", country.getName());

            return parameters;
        }

    }
}
