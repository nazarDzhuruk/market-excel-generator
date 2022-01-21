package com.company.name.googledrivemanager.database.service;

import com.company.name.googledrivemanager.database.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    void cancelOrderById(int orderID);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    List<Order> getOrdersByDate(String date);
}
