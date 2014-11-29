/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author philb
 */
public class Money {

    MathContext mc = new MathContext(7, RoundingMode.HALF_EVEN);
    private BigDecimal value = BigDecimal.ZERO;

    public Money() {
        this(BigDecimal.ZERO);
    }

    public Money(BigDecimal b) {
        this.value = b;
    }

    public Money(Integer i) {
        this.value = new BigDecimal(i);
    }

    public Money(Long l) {
        this.value = new BigDecimal(l);
    }

    public Money(Double d) {
        this.value = new BigDecimal(d);
    }

    public Money(String s) {
        this.value = new BigDecimal(s);
    }

    public boolean isZero() {
        return (value.compareTo(BigDecimal.ZERO) == 0);
    }

    public boolean isEqual(Money m) {
        return (m.value.compareTo(value) == 0);
    }

    public boolean isEqual(BigDecimal d) {
        return (d.compareTo(value) == 0);
    }

    public boolean exceeds(Money m) {
        return (m.value.compareTo(value) > 0);
    }

    public boolean exceedsOrEquals(Money m) {
        return (m.value.compareTo(value) >= 0);
    }

    public boolean exceeds(BigDecimal d) {
        return (d.compareTo(value) > 0);
    }

    public boolean exceedsOrEquals(BigDecimal d) {
        return (d.compareTo(value) >= 0);
    }

    public Money add(Money m) {
        this.value = this.value.add(m.value, mc);
        return this;
    }

    public Money add(BigDecimal b) {
        return add(new Money(b));
    }

    public Money add(Integer b) {
        return add(new Money(b));
    }

    public Money add(Long b) {
        return add(new Money(b));
    }

    public Money add(Double b) {
        return add(new Money(b));
    }

    public Money subtract(Money m) {
        this.value = this.value.subtract(m.value, mc);
        return this;
    }

    public Money subtract(BigDecimal b) {
        return subtract(new Money(b));
    }

    public Money subtract(Integer b) {
        return subtract(new Money(b));
    }

    public Money subtract(Long b) {
        return subtract(new Money(b));
    }

    public Money subtract(Double b) {
        return subtract(new Money(b));
    }

    public Money multiply(Money m) {
        this.value = this.value.multiply(m.value, mc);
        return this;
    }

    public Money multiply(BigDecimal b) {
        return multiply(new Money(b));
    }

    public Money multiply(Integer b) {
        return multiply(new Money(b));
    }

    public Money multiply(Long b) {
        return multiply(new Money(b));
    }

    public Money multiply(Double b) {
        return multiply(new Money(b));
    }

    public Money x(Money m) {
        return multiply(m);
    }

    public Money x(BigDecimal b) {
        return multiply(new Money(b));
    }

    public Money x(Integer b) {
        return multiply(new Money(b));
    }

    public Money x(Long b) {
        return multiply(new Money(b));
    }

    public Money x(Double b) {
        return multiply(new Money(b));
    }

    public Money divide(Money m) {
        this.value = this.value.divide(m.value, mc);
        return this;
    }

    public Money divide(BigDecimal b) {
        return divide(new Money(b));
    }

    public Money divide(Integer b) {
        return divide(new Money(b));
    }

    public Money divide(Long b) {
        return divide(new Money(b));
    }

    public Money divide(Double b) {
        return divide(new Money(b));
    }
    
    public BigDecimal asBigDecimal() {
        return value;
    }
}
