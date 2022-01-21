package com.company.name.googledrivemanager.database.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderedProduct {
    @Id
    private Integer id;
    private int productCode;
    private int productQuantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    Set<Order> orders = new HashSet<>();

    public OrderedProduct(int id, int productCode, int productQuantity) {
        this.id = id;
        this.productCode = productCode;
        this.productQuantity = productQuantity;
    }

    protected OrderedProduct(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
