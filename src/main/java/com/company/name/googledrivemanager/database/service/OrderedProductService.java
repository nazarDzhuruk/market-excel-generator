package com.company.name.googledrivemanager.database.service;

import com.company.name.googledrivemanager.database.model.OrderedProduct;

public interface OrderedProductService {
    void assignProductToOrder(int orderId, OrderedProduct orderedProduct);

    void removeProductFromOrder(int orderId, int productId);
}

