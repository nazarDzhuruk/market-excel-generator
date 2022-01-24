package com.company.name.googledrivemanager.database.service.impl;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.OrderRepository;
import com.company.name.googledrivemanager.database.repository.ProductRepository;
import com.company.name.googledrivemanager.database.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    private OrderService underTest;

    @BeforeEach
    void setUp(){
        underTest = new OrderServiceImpl(orderRepository, productRepository);
    }

    @Test
    void createOrder() {

        List<Product> products = new ArrayList<>();


    }

    @Test
    @Disabled
    void cancelOrderById() {
    }

    @Test
    void getOrderById() {

    }

    @Test
    void canGetAllOrders() {
        //when
        underTest.getAllOrders();

        //then
        verify(orderRepository).findAll();
    }

    @Test
    void getOrdersByDate() {

    }
}