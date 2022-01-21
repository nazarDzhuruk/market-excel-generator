package com.company.name.googledrivemanager.database.service.impl;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.OrderRepository;
import com.company.name.googledrivemanager.database.repository.ProductRepository;
import com.company.name.googledrivemanager.database.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderServiceImpl(OrderRepository repository, ProductRepository productRepository) {
        this.orderRepository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);

//        if (order.getProducts() != null) {
//            changeQuantityOfStoredItem(order.getProducts());
//        }
    }

    @Override
    public void cancelOrderById(int orderID) {
        orderRepository.deleteById(orderID);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return orderRepository.findAll().stream().filter(order -> String.valueOf(order.getDate()).equals(date))
                .collect(Collectors.toList());
    }
}
