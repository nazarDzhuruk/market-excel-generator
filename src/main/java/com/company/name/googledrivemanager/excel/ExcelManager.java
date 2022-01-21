package com.company.name.googledrivemanager.excel;

public interface ExcelManager {
    void generateExcelFromExistData(String documentName, String ordersDate);
    void clearSheetFolder(String documentName);

}
