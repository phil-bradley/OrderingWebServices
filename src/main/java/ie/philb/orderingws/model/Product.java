/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.model;

import java.util.Objects;

/**
 *
 * @author philb
 */
public class Product {

    private String skuCode;
    private String description;
    private String title;
    private Money unitPrice;

    public Product(String skuCode, String description, String title, Money unitPrice) {
        this.skuCode = skuCode;
        this.description = description;
        this.title = title;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.skuCode);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.unitPrice);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.skuCode, other.skuCode)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        return true;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

}
