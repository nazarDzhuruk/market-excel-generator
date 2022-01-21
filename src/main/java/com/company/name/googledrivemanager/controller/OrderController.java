package com.company.name.googledrivemanager.controller;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.database.service.OrderedProductService;
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
    private final OrderedProductService orderedProductService;
    private final ProductService productService;


    public OrderController(OrderService orderService, ProductService productService,
                           OrderedProductService orderedProductService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderedProductService = orderedProductService;
    }

    @GetMapping
    public List<Order> allOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create/")
    public void createOrder(@RequestBody @Valid Order order) {

//
//        List<Product> products = order.getProducts().stream()
//                .map(product -> productService.findByProductCode(product.getProductCode()))
//                .collect(Collectors.toList());
//
//
//        products.forEach(productService::create);
        orderService.createOrder(order);

    }

    @PutMapping("/create/{orderId}/")
    public ResponseEntity<Order> addProducts(@Valid @PathVariable("orderId") Integer orderId,
                                             @Valid @RequestBody OrderedProduct orderedProduct) {

        orderedProductService.assignProductToOrder(orderId, orderedProduct);

        return new ResponseEntity<>(HttpStatus.OK);
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
