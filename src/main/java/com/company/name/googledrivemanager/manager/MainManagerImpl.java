package com.company.name.googledrivemanager.manager;

import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.excel.ExcelManager;
import com.company.name.googledrivemanager.google.DriveManager;
import org.springframework.stereotype.Component;

@Component
public class MainManagerImpl implements MainManager {
    private final DriveManager driveManager;
    private final OrderService orderService;
    private final ExcelManager excelManager;

    public MainManagerImpl(DriveManager driveManager, OrderService orderService, ExcelManager excelManager) {
        this.driveManager = driveManager;
        this.orderService = orderService;
        this.excelManager = excelManager;
    }

    @Override
    public void generateDailyExcel(String date) {
        excelManager.generateExcelFromExistData(date, date);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driveManager.uploadGoogleFile(date, date, date);
    }

}
