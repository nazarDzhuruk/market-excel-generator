package com.company.name.googledrivemanager.database.service.impl;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.OrderRepository;
import com.company.name.googledrivemanager.database.repository.OrderedProductRepository;
import com.company.name.googledrivemanager.database.repository.ProductRepository;
import com.company.name.googledrivemanager.database.service.OrderedProductService;
import org.springframework.stereotype.Service;

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

        if (order != null) {
            orderedProduct.getOrders().add(order);
            changeQuantityOfStoredItem(orderedProduct);
            orderedProductRepository.save(orderedProduct);
        }else {
            throw new NullPointerException("Order not found");
        }
    }

    @Override
    public void removeProductFromOrder(int orderId, int productId) {

    }

    private void changeQuantityOfStoredItem(OrderedProduct orderedItem) {

        Product product = productRepository.findById(orderedItem.getProductCode())
                .orElseThrow(() -> new NullPointerException("Product not found"));

        if (product != null && product.getProductQuantity() > orderedItem.getProductQuantity()) {
            int newQuantity = product.getProductQuantity() - orderedItem.getProductQuantity();
            product.setProductQuantity(newQuantity);
            productRepository.save(product);
        } else {
            throw new NullPointerException("Product not found");
        }
    }
}
