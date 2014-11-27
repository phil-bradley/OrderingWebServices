/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.jdbc;

/**
 *
 * @author philb
 */
public interface EntityMapper<T> {

    public JdbcParameterSet getParameterSet(T t);

}
