/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.model;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetail implements Serializable {

    private String skuCode;
    private String description;
    private int quantity;
    private Money unitPrice;
    private Money lineTotal;
    private Long id;

    public OrderDetail(String skuCode, String description, int quantity, Money unitPrice, Money lineTotal, Long id) {
        this.skuCode = skuCode;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.id = id;
    }

    public OrderDetail() {
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Money getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Money lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.skuCode);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + this.quantity;
        hash = 37 * hash + Objects.hashCode(this.unitPrice);
        hash = 37 * hash + Objects.hashCode(this.lineTotal);
        hash = 37 * hash + Objects.hashCode(this.id);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDetail other = (OrderDetail) obj;
        if (!Objects.equals(this.skuCode, other.skuCode)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        if (!Objects.equals(this.lineTotal, other.lineTotal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "skuCode=" + skuCode + ", description=" + description + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", lineTotal=" + lineTotal + ", lineId=" + id + '}';
    }

}
