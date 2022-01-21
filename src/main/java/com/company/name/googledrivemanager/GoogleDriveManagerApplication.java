package com.company.name.googledrivemanager;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import com.company.name.googledrivemanager.database.model.Product;
import com.company.name.googledrivemanager.database.repository.OrderedProductRepository;
import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.database.service.ProductService;
import com.company.name.googledrivemanager.excel.ExcelManager;
import com.company.name.googledrivemanager.google.DriveManager;
import com.company.name.googledrivemanager.manager.MainManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class GoogleDriveManagerApplication {
    public static ExcelManager excelManager;
    public static DriveManager driveManager;
    public static OrderService orderService;
    public static ProductService productService;
    private static MainManager mainManager;
    private static OrderedProductRepository orderedProductRepository;


    public GoogleDriveManagerApplication(DriveManager driveManager, OrderService orderService,
                                         ProductService productService, ExcelManager excelManager,
                                         OrderedProductRepository orderedProductRepository,
                                         MainManager mainManager) {
        this.driveManager = driveManager;
        this.orderService = orderService;
        this.productService = productService;
        this.excelManager = excelManager;
        this.mainManager = mainManager;
        this.orderedProductRepository = orderedProductRepository;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GoogleDriveManagerApplication.class, args);
        Product product = new Product("Name", 123, 5, "me");
        Product product1 = new Product("Name1", 1233, 53, "me");
        Product product2 = new Product("Name2", 1234, 4, "me");
        Product product3 = new Product("Name3", 12345, 1, "me");
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.forEach(p -> productService.create(p));
        OrderedProduct orderedProducts = new OrderedProduct(1, 1233, 50);

        Set<OrderedProduct> ordered = new HashSet<>();

        ordered.add(orderedProducts);

        Order order = new Order(13, "Krakow", true, LocalDate.now());

        ordered.forEach(p -> p.getOrders().add(order));
        orderedProductRepository.save(orderedProducts);
        orderService.createOrder(order);

        productService.storedItems().forEach(System.out::println);
    }

}