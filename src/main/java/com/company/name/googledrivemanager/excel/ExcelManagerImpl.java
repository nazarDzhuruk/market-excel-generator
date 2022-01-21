package com.company.name.googledrivemanager.excel;

import com.company.name.googledrivemanager.database.service.OrderService;
import com.company.name.googledrivemanager.google.services.DriveDataManager;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ExcelManagerImpl implements ExcelManager {
    private static final String PATH = "src/main/sheets";
    private final ExcelDocument excelDocument = ExcelDocument.getInstance();
    private final OrderService orderService;

    public ExcelManagerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void generateExcelFromExistData(String documentName, String ordersDate) {
        try {
            if (DriveDataManager.getGoogleFilesByName(documentName).isEmpty()) {
                excelDocument.createDocument(documentName, orderService.getOrdersByDate(ordersDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void clearSheetFolder(String documentName) {
        File file = new File(PATH);
        try {
            if(!DriveDataManager.getGoogleFilesByName(documentName).isEmpty()){
                Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(File::delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
