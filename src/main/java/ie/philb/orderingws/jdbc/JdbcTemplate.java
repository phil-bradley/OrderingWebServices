/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author pbradley
 */
public class JdbcTemplate {

    protected static final Logger logger = Logger.getLogger(JdbcTemplate.class.getSimpleName());

    private final DataSource ds;
    private int limit = 0;
    private boolean usingPreparedStatements = false;

    public JdbcTemplate(DataSource ds) {
        this.ds = ds;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isUsingPreparedStatements() {
        return usingPreparedStatements;
    }

    public void setUsingPreparedStatements(boolean usingPreparedStatements) {
        this.usingPreparedStatements = usingPreparedStatements;
    }

    public <T> T querySingleResult(String sql, RowMapper<T> mapper) throws JdbcException, NoSuchEntityException {
        return querySingleResult(sql, new JdbcParameterSet(), mapper);
    }

    public <T> T querySingleResult(String sql, JdbcParameterSet parameterSet, RowMapper<T> mapper) throws JdbcException, NoSuchEntityException {

        T t = null;

        Map<Integer, String> parametersNamesByIndex = getParameterNamesByIndex(sql);
        Map<Integer, JdbcParameter> parametersByIndex = new HashMap<>();

        for (Integer index : parametersNamesByIndex.keySet()) {
            String name = parametersNamesByIndex.get(index);

            for (JdbcParameter p : parameterSet.getParameters()) {
                if (p.getKey().equalsIgnoreCase(name)) {
                    parametersByIndex.put(index, p);
                }
            }
        }

        String sqlWithPlaceholders = namedParametersToPlaceholders(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sqlWithPlaceholders);

            if (limit > 0) {
                ps.setMaxRows(limit);
            }

            for (Integer index : parametersByIndex.keySet()) {
                JdbcParameter parameter = parametersByIndex.get(index);

                if (parameter instanceof LongJdbcParameter) {
                    LongJdbcParameter lp = (LongJdbcParameter) parameter;
                    ps.setLong(index, lp.getValue());
                }

                if (parameter instanceof BigDecimalJdbcParameter) {
                    BigDecimalJdbcParameter bdp = (BigDecimalJdbcParameter) parameter;
                    ps.setBigDecimal(index, bdp.getValue());
                }

                if (parameter instanceof StringJdbcParameter) {
                    StringJdbcParameter sdp = (StringJdbcParameter) parameter;
                    ps.setString(index, sdp.getValue());
                }
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                t = mapper.mapRow(rs);
            } else {
                throw new NoSuchEntityException();
            }

        } catch (SQLException ex) {
            throw new JdbcException(sqlWithPlaceholders, ex);
        } finally {
            closeResources(conn, ps, rs);
        }

        return t;
    }

    public <T> List<T> queryResultList(String sql, RowMapper<T> mapper) throws JdbcException {
        return queryResultList(sql, new JdbcParameterSet(), mapper);
    }

    public <T> List<T> queryResultList(String sql, JdbcParameterSet parameterSet, RowMapper<T> mapper) throws JdbcException {

        List<T> results = new ArrayList<>();

        Map<Integer, String> parametersNamesByIndex = getParameterNamesByIndex(sql);
        Map<Integer, JdbcParameter> parametersByIndex = new HashMap<>();

        for (Integer index : parametersNamesByIndex.keySet()) {
            String name = parametersNamesByIndex.get(index);

            for (JdbcParameter p : parameterSet.getParameters()) {
                if (p.getKey().equalsIgnoreCase(name)) {
                    parametersByIndex.put(index, p);
                }
            }
        }

        String sqlWithPlaceholders = namedParametersToPlaceholders(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sqlWithPlaceholders);

            if (limit > 0) {
                ps.setMaxRows(limit);
            }

            for (Integer index : parametersByIndex.keySet()) {
                JdbcParameter parameter = parametersByIndex.get(index);

                if (parameter instanceof LongJdbcParameter) {
                    LongJdbcParameter lp = (LongJdbcParameter) parameter;
                    ps.setLong(index, lp.getValue());
                }

                if (parameter instanceof BigDecimalJdbcParameter) {
                    BigDecimalJdbcParameter bdp = (BigDecimalJdbcParameter) parameter;
                    ps.setBigDecimal(index, bdp.getValue());
                }

                if (parameter instanceof StringJdbcParameter) {
                    StringJdbcParameter sdp = (StringJdbcParameter) parameter;
                    ps.setString(index, sdp.getValue());
                }
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                T t = mapper.mapRow(rs);
                results.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new JdbcException(sqlWithPlaceholders, ex);
        } finally {
            closeResources(conn, ps, rs);
        }

        return results;
    }

    public long executeInsert(String sql, JdbcParameterSet parameterSet) throws JdbcException {

        long id = 0;

        Map<Integer, String> parametersNamesByIndex = getParameterNamesByIndex(sql);
        Map<Integer, JdbcParameter> parametersByIndex = new HashMap<>();

        for (Integer index : parametersNamesByIndex.keySet()) {
            String name = parametersNamesByIndex.get(index);

            for (JdbcParameter p : parameterSet.getParameters()) {
                if (p.getKey().equalsIgnoreCase(name)) {
                    parametersByIndex.put(index, p);
                }
            }
        }

        String sqlWithPlaceholders = namedParametersToPlaceholders(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sqlWithPlaceholders, Statement.RETURN_GENERATED_KEYS);

            if (limit > 0) {
                ps.setMaxRows(limit);
            }

            for (Integer index : parametersByIndex.keySet()) {
                JdbcParameter parameter = parametersByIndex.get(index);

                if (parameter instanceof LongJdbcParameter) {
                    LongJdbcParameter lp = (LongJdbcParameter) parameter;
                    ps.setLong(index, lp.getValue());
                }

                if (parameter instanceof BigDecimalJdbcParameter) {
                    BigDecimalJdbcParameter bdp = (BigDecimalJdbcParameter) parameter;
                    ps.setBigDecimal(index, bdp.getValue());
                }

                if (parameter instanceof StringJdbcParameter) {
                    StringJdbcParameter sdp = (StringJdbcParameter) parameter;
                    ps.setString(index, sdp.getValue());
                }
            }

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new JdbcException(sqlWithPlaceholders, ex);
        } finally {
            closeResources(conn, ps, rs);
        }

        return id;
    }

    public int executeUpdate(String sql, JdbcParameterSet parameterSet) throws JdbcException {
        Map<Integer, String> parametersNamesByIndex = getParameterNamesByIndex(sql);
        Map<Integer, JdbcParameter> parametersByIndex = new HashMap<>();

        for (Integer index : parametersNamesByIndex.keySet()) {
            String name = parametersNamesByIndex.get(index);

            for (JdbcParameter p : parameterSet.getParameters()) {
                if (p.getKey().equalsIgnoreCase(name)) {
                    parametersByIndex.put(index, p);
                }
            }
        }

        String sqlWithPlaceholders = namedParametersToPlaceholders(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sqlWithPlaceholders);

            if (limit > 0) {
                ps.setMaxRows(limit);
            }

            for (Integer index : parametersByIndex.keySet()) {
                JdbcParameter parameter = parametersByIndex.get(index);

                if (parameter instanceof LongJdbcParameter) {
                    LongJdbcParameter lp = (LongJdbcParameter) parameter;
                    ps.setLong(index, lp.getValue());
                }

                if (parameter instanceof BigDecimalJdbcParameter) {
                    BigDecimalJdbcParameter bdp = (BigDecimalJdbcParameter) parameter;
                    ps.setBigDecimal(index, bdp.getValue());
                }

                if (parameter instanceof StringJdbcParameter) {
                    StringJdbcParameter sdp = (StringJdbcParameter) parameter;
                    ps.setString(index, sdp.getValue());
                }
            }

            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new JdbcException(sqlWithPlaceholders, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    private void closeResources(Connection conn, Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }

    private void closeStatement(Statement st) {
        if (st == null) {
            return;
        }

        try {
            st.close();
        } catch (Exception ex) {

        }
    }

    private void closeResultSet(ResultSet rs) {
        if (rs == null) {
            return;
        }

        try {
            rs.close();
        } catch (Exception ex) {

        }
    }

    private void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }

        try {
            conn.close();
        } catch (Exception ex) {

        }
    }

    private <T> boolean containsParameter(String sql, JdbcParameter<T> p) {
        String k = ":" + p.getKey();
        return sql.contains(k);
    }

    private Map<Integer, String> getParameterNamesByIndex(String sql) {
        Map<Integer, String> parametersByIndex = new HashMap<Integer, String>();

        String[] tokens = sql.split(" ");
        int index = 1;

        for (String token : tokens) {

            if (token.startsWith(":")) {
                parametersByIndex.put(index, token.substring(1));
                index++;
            }
        }

        return parametersByIndex;
    }

    private String namedParametersToPlaceholders(String sql) {

        String[] tokens = sql.split(" ");

        StringBuilder sqlWithPlaceholders = new StringBuilder();

        for (String token : tokens) {

            if (token.startsWith(":")) {
                sqlWithPlaceholders.append(" ? ");
            } else {
                sqlWithPlaceholders.append(" ").append(token);
            }
        }

        return sqlWithPlaceholders.toString().trim();
    }
}
