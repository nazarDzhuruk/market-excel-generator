package com.company.name.googledrivemanager.controller;

import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> availableItems() {
        return productService.storedItems();
    }

    @GetMapping("/{id}")
    public Product productById(@PathVariable("id") Integer id) {
        return productService.findByProductCode(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody @Valid Product product) {
        productService.create(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> removeProduct(@PathVariable("id") Integer id) {

        Product product = productService.findByProductCode(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteByProductCode(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
