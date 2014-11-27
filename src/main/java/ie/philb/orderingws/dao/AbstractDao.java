/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.dao;

import ie.philb.orderingws.jdbc.JdbcTemplate;
import java.util.List;
import javax.sql.DataSource;
import lombok.NonNull;

/**
 *
 * @author philb
 * @param <T>
 */
public abstract class AbstractDao<T> {

    private final DataSource ds;
    private final JdbcTemplate jdbcTemplate;

    public AbstractDao(@NonNull DataSource ds) {
        this.ds = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public abstract T get(Long id) throws DaoException, NoSuchEntityDaoException;

    public abstract long count() throws DaoException;

    public abstract long create(T t) throws DaoException;

    public abstract int update(T t) throws DaoException, NoSuchEntityDaoException;

    public abstract int delete(Long id) throws DaoException;

    public abstract List<T> list() throws DaoException;
}
