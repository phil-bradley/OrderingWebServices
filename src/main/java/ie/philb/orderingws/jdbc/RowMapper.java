/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pbradley
 */
public interface RowMapper<T> {
    
    public T mapRow(ResultSet rs) throws SQLException;
        
}
