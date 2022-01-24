package com.company.name.googledrivemanager.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int productCode;
    private int productQuantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>();

    public OrderedProduct(OrderedProductBuilder orderedProductBuilder) {
        this.productCode = orderedProductBuilder.productCode;
        this.productQuantity = orderedProductBuilder.productQuantity;
    }

    protected OrderedProduct() {
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

    public static class OrderedProductBuilder {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("productCode")
        private int productCode;
        @JsonProperty("productQuantity")
        private int productQuantity;

        public OrderedProductBuilder(Integer id, int productCode, int productQuantity) {
            this.id = id;
            this.productCode = productCode;
            this.productQuantity = productQuantity;
        }

        public OrderedProduct build() {
            return new OrderedProduct(this);
        }
    }
}
