package com.company.name.googledrivemanager.controller;

import static com.company.name.googledrivemanager.database.model.Order.OrderBuilder;
import static com.company.name.googledrivemanager.database.model.OrderedProduct.OrderedProductBuilder;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.database.service.OrderedProductService;
import com.company.name.googledrivemanager.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderedProductService orderedProductService;


    public OrderController(OrderService orderService, OrderedProductService orderedProductService) {
        this.orderService = orderService;
        this.orderedProductService = orderedProductService;
    }

    @GetMapping
    public List<Order> allOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create/")
    public void createOrder(@Valid @RequestBody OrderBuilder orderBuilder) {
        Order order = orderBuilder.build();

        orderService.createOrder(order);
    }

    @PutMapping("/create/{orderId}/")
    public ResponseEntity<Order> addProducts(@Valid @PathVariable("orderId") Integer orderId,
                                             @Valid @RequestBody OrderedProductBuilder orderedProductBuilder) {
        OrderedProduct orderedProduct = orderedProductBuilder.build();

        try {
            orderService.getOrderById(orderId);
        } catch (NoSuchElementException e) {
            throw new ApiRequestException("Order not found");
        }
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
