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
import ie.philb.orderingws.model.Money;
import ie.philb.orderingws.model.Order;
import ie.philb.orderingws.model.OrderDetail;
import ie.philb.orderingws.model.Party;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.NonNull;

public class OrderDao {

    private final OrderMapper orderMapper = new OrderMapper();
    private final OrderDetailMapper orderDetailMapper = new OrderDetailMapper();
    private final JdbcTemplate jdbcTemplate;

    public OrderDao(@NonNull DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public Order get(Long id) throws DaoException, NoSuchEntityDaoException {
        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("id", id);

            Order order = getJdbcTemplate().querySingleResult("SELECT * FROM orderheader WHERE id = :id", params, orderMapper);
            return order;
        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get product " + id), jdx);
        } catch (NoSuchEntityException nx) {
            throw new NoSuchEntityDaoException("Failed to get order " + id);
        }
    }

    public List<Order> getOrdersByBuyer(Long buyerId) throws DaoException {

        List<Order> orders = new ArrayList<>();

        try {
            JdbcParameterSet params = new JdbcParameterSet();
            params.add("BUYERID", buyerId);

            orders = getJdbcTemplate().queryResultList("SELECT * FROM order WHERE buyerid = :BUYERID", params, orderMapper);

            List<OrderDetail> details = getJdbcTemplate().queryResultList("SELECT * FROM orderdetail WHERE orderid IN (SELECT id FROM order WHERE buyerid = :BUYERID)", params, orderDetailMapper);

            for (Order order : orders) {
                for (OrderDetail detail : details) {
                    if (Objects.equals(detail.getOrderId(), order.getId())) {
                        order.getDetail().add(detail);
                    }
                }
            }

        } catch (JdbcException jdx) {
            throw new DaoException(("Failed to get orders for buyer " + buyerId), jdx);
        }

        return orders;
    }

    private List<OrderDetail> getOrderDetails(Long orderId) throws DaoException {
        try {
            List<OrderDetail> orderDetails = getJdbcTemplate().queryResultList("SELECT * FROM orderdetail WHERE orderid = :ORDERID", orderDetailMapper);
            return orderDetails;
        } catch (JdbcException jdx) {
            throw new DaoException("Failed to get order details", jdx);
        }

    }

    class OrderMapper implements RowMapper<Order>, EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("ID"));
            order.setCreated(rs.getTimestamp("CREATED"));
            order.setSubmitted(rs.getTimestamp("SUBMITTED"));

            Party buyer = new Party();
            buyer.setId(rs.getLong("BUYERID"));

            order.setBuyer(buyer);
            return order;
        }

        @Override
        public JdbcParameterSet getParameterSet(Order order) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("ID", order.getId());
            parameters.add("CREATED", order.getCreated());
            parameters.add("SUBMITTED", order.getSubmitted());
            parameters.add("BUYERID", order.getBuyer().getId());
            return parameters;
        }
    }

    class OrderDetailMapper implements RowMapper<OrderDetail>, EntityMapper<OrderDetail> {

        @Override
        public OrderDetail mapRow(ResultSet rs) throws SQLException {
            OrderDetail detail = new OrderDetail();
            detail.setId(rs.getLong("ID"));
            detail.setSkuCode(rs.getString("SKUCODE"));
            detail.setQuantity(rs.getInt("QUANTITY"));
            detail.setUnitPrice(new Money(rs.getBigDecimal("UNITPRICE")));
            detail.setLineTotal(new Money(rs.getBigDecimal("LINETOTAL")));
            detail.setOrderId(rs.getLong("ORDERID"));

            return detail;
        }

        @Override
        public JdbcParameterSet getParameterSet(OrderDetail detail) {
            JdbcParameterSet parameters = new JdbcParameterSet();
            parameters.add("ID", detail.getId());
            parameters.add("SKUCODE", detail.getSkuCode());
            parameters.add("QUANTITY", detail.getQuantity());
            parameters.add("UNITPRICE", detail.getUnitPrice().asBigDecimal());
            parameters.add("LINETOTAL", detail.getLineTotal().asBigDecimal());
            parameters.add("ORDERID", detail.getOrderId());

            return parameters;
        }
    }
}
