package com.company.name.googledrivemanager.database.service.impl;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.OrderRepository;
import com.company.name.googledrivemanager.database.repository.OrderedProductRepository;
import com.company.name.googledrivemanager.database.repository.ProductRepository;
import com.company.name.googledrivemanager.database.service.OrderedProductService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderedProductService {
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderProductServiceImpl(OrderedProductRepository orderedProductRepository,
                                   ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void assignProductToOrder(int orderId, OrderedProduct orderedProduct) {
        Order order = orderRepository.findById(orderId).orElse(null);
        orderedProduct.getOrders().add(order);
        changeQuantityOfStoredItem(orderedProduct);
        orderedProductRepository.save(orderedProduct);
    }

    @Override
    public void removeProductFromOrder(int orderId, int productId) {

    }

    private void changeQuantityOfStoredItem(OrderedProduct orderedItem) {
        Product product = productRepository.findById(orderedItem.getProductCode()).orElse(null);
        if (product != null) {
            int newQuantity = product.getProductQuantity() - orderedItem.getProductQuantity();
            product.setProductQuantity(newQuantity);
            productRepository.save(product);
        } else {
            throw new NullPointerException("Product not found");
        }
    }
}
