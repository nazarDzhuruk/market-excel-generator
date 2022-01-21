package com.company.name.googledrivemanager.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Product")
@Table(name = "product")
public class Product implements Comparable<Product> {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Integer productCode;
    @Column(name = "name")
    private String name;
    @Column(name = "product_distributor", nullable = false)
    private String productDistributor;
    @Column(name = "quantity", nullable = false)
    private int productQuantity;

    public Product(String name, Integer productCode, int productQuantity, String productDistributor) {
        this.name = name;
        this.productCode = productCode;
        this.productDistributor = productDistributor;
        this.productQuantity = productQuantity;
    }

    public Product() {
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductDistributor() {
        return productDistributor;
    }

    public void setProductDistributor(String productDistributor) {
        this.productDistributor = productDistributor;
    }

    @Override
    public int compareTo(Product product) {
        return this.productCode.compareTo(product.getProductCode());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", name='" + name + '\'' +
                ", productDistributor='" + productDistributor + '\'' +
                ", productQuantity=" + productQuantity +
                '}';
    }
}