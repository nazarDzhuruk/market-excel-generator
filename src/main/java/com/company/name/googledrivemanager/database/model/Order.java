package com.company.name.googledrivemanager.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    private Set<OrderedProduct> products = new HashSet<>();

    public Order(OrderBuilder orderBuilder) {
        this.orderID = orderBuilder.id;
        this.destination = orderBuilder.destination;
        this.paymentStatus = orderBuilder.paymentStatus;
        this.date = orderBuilder.date;
    }

    protected Order() {
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

    public boolean isPaymentStatusTrue() {
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

    public static class OrderBuilder {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("destination")
        private String destination;
        @JsonProperty("paymentStatus")
        private boolean paymentStatus;
        @JsonProperty("date")
        private LocalDate date;

        public OrderBuilder(Integer id, String destination, boolean paymentStatus, LocalDate date) {
            this.id = id;
            this.destination = destination;
            this.paymentStatus = paymentStatus;
            this.date = date;
        }

        public Order build() {
            return new Order(this);
        }
    }
}