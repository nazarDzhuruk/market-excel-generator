package com.company.name.googledrivemanager;

import com.company.name.googledrivemanager.database.repository.OrderedProductRepository;
import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.database.service.ProductService;
import com.company.name.googledrivemanager.excel.ExcelManager;
import com.company.name.googledrivemanager.google.DriveManager;
import com.company.name.googledrivemanager.manager.MainManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

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
    }

}