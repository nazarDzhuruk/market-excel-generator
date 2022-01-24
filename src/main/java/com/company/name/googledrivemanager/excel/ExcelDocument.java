package com.company.name.googledrivemanager.excel;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.OrderedProduct;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExcelDocument {
    private static final String PATH = "src/main/sheets";
    private static final String EXTENSION = ".xlsx";
    private static final String SLASH = "/";
    private static ExcelDocument INSTANCE;


    private ExcelDocument() {
    }

    public static ExcelDocument getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExcelDocument();
        }
        return INSTANCE;
    }

    public static void createDocument(String documentName, List<Order> data) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(String.valueOf(LocalDate.now()));
        int rowNum = 0;
        for (Order order : data) {
            for (OrderedProduct product : order.getProducts()) {
                Row row = sheet.createRow(rowNum++);
                createCell(order, product, row);
            }
        }
        try (OutputStream fileOut = new FileOutputStream(PATH + SLASH + documentName + EXTENSION)) {

            wb.write(fileOut);
            wb.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createCell(Order order, OrderedProduct product, Row row) {
        Cell idCell = row.createCell(0);
        Cell dateCell = row.createCell(1);
        Cell destinationCell = row.createCell(2);
        Cell productCell = row.createCell(3);
        Cell productQuantityCell = row.createCell(4);

        idCell.setCellValue(order.getOrderID());
        dateCell.setCellValue(String.valueOf(order.getDate()));
        destinationCell.setCellValue(order.getDestination());
        productCell.setCellValue(product.getProductCode());
        productQuantityCell.setCellValue(product.getProductQuantity());
    }

}
