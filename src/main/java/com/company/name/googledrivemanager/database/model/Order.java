package com.company.name.googledrivemanager.database.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Order(Integer orderID, String destination, boolean paymentStatus, LocalDate date, List<Product> products) {
        this.orderID = orderID;
        this.destination = destination;
        this.paymentStatus = paymentStatus;
        this.date = date;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", destination='" + destination + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", date=" + date +
                ", orderedProducts=" + products +
                '}';
    }
}