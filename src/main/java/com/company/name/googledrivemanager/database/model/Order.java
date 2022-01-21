package com.company.name.googledrivemanager.database.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer orderID;
    @Column(name = "destination", nullable = false, updatable = false)
    private String destination;
    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;
    @Column(name = "datetime", nullable = true)
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    @JsonIgnore
    private Set<OrderedProduct> products = new HashSet<>();

    public Order(Integer orderID, String destination, boolean paymentStatus, LocalDate date) {
        this.orderID = orderID;
        this.destination = destination;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    public Order() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderedProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", destination='" + destination + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", date=" + date +
                '}';
    }
}