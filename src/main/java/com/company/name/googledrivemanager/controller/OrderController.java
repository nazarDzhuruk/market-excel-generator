package com.company.name.googledrivemanager.controller;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.database.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;


    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public List<Order> allOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create/")
    public void createOrder(@RequestBody @Valid Order order) {
        System.out.println(order.getProducts().toString());
//
//        List<Product> products = order.getProducts().stream()
//                .map(product -> productService.findByProductCode(product.getProductCode()))
//                .collect(Collectors.toList());
//
//
//        products.forEach(productService::create);
        orderService.createOrder(order);

    }

    @PutMapping("/take/{orderId}/")
    public ResponseEntity<Order> addProducts(@Valid @PathVariable("orderId") Integer orderId,
                                             @Valid @RequestBody Product product) {
        Order order = orderService.getOrderById(orderId);
        order.getProducts().add(product);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.cancelOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
