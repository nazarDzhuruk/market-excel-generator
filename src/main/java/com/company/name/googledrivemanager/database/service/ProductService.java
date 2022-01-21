package com.company.name.googledrivemanager.database.service;

import com.company.name.googledrivemanager.database.model.Product;

import java.util.List;

public interface ProductService {
    void create(Product product);
    void deleteByProductCode(int productCode);
    Product findByProductCode(int productCode);
    List<Product> storedItems();
}
