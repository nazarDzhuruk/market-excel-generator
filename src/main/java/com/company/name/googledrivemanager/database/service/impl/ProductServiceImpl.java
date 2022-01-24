package com.company.name.googledrivemanager.database.service.impl;

import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.ProductRepository;
import com.company.name.googledrivemanager.database.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Product product) {
        repository.save(product);
    }

    @Override
    public void deleteByProductCode(int productCode) {
        repository.deleteById(productCode);
    }

    @Override
    public Product findByProductCode(int productCode) {
        return repository.findById(productCode).stream().findAny().orElse(null);
    }

    @Override
    public List<Product> storedItems() {
        return repository.findAll();
    }
}
