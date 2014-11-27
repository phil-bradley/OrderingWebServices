/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.jdbc;

/**
 *
 * @author pbradley
 */
public class JdbcException extends Exception {
    
    private final String sql;
    
    public JdbcException(String sql, Throwable ex) {
        super("SQL Failed  -->" + sql + "<--", ex);
        this.sql = sql;
    }
    
    public String getSql() {
        return sql;
    }
}
