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
import ie.philb.orderingws.model.Party;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author philb
 */
public class PartyDao extends AbstractDao<Party> {

    private final PartyMapper partyMapper = new PartyMapper();

    public PartyDao(DataSource ds) {
        super(ds);
    }

    @Override
    public Party get(Long id) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("id", id);

            Party p = getJdbcTemplate().querySingleResult("SELECT * FROM party WHERE id = :id", params, partyMapper);
            return p;                       
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get party " + id), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get party " + id);
        }
    }

    @Override
    public long count() throws DaoException {
        try {
            return getJdbcTemplate().querySingleResult("SELECT count(*) FROM party", (ResultSet rs) -> rs.getLong(1));
        } catch (JdbcException | NoSuchEntityException jdx) {
            throw new DaoException("Failed to get party count", jdx);
        }
    }

    @Override
    public long create(Party t) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Party t) throws DaoException, NoSuchEntityDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = "DELETE FROM party WHERE id = :ID";

        JdbcParameterSet params = new JdbcParameterSet();
        params.add("id", id);

        try {
            return getJdbcTemplate().executeUpdate(sql, params);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to delete party", jdx);
        }
    }

    @Override
    public List<Party> list() throws DaoException {
        try {
            return getJdbcTemplate().queryResultList("SELECT * FROM party", partyMapper);
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get parties", jdx);
        }
    }

    class PartyMapper implements RowMapper<Party>, EntityMapper<Party> {

        @Override
        public Party mapRow(ResultSet rs) throws SQLException {
            Party p = new Party();
            p.setId(rs.getLong("ID"));
            p.setName(rs.getString("NAME"));
            p.setTelephone(rs.getString("PHONE"));
            p.setEmail(rs.getString("EMAIL"));

            return p;
        }

        @Override
        public JdbcParameterSet getParameterSet(Party p) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("ID", p.getId());
            parameters.add("NAME", p.getName());
            parameters.add("PHONE", p.getTelephone());
            parameters.add("EMAIL", p.getEmail());

            return parameters;
        }

    }

}
