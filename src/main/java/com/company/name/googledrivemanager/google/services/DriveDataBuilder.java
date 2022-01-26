package com.company.name.googledrivemanager.google.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;

public class DriveDataBuilder {
    private static final String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";
    private static final String SHEETS_FOLDER = "/Users/nazardzh/Downloads/sheets/";

    public static void createGoogleFolder(String folderIdParent, String folderName) {
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType(FOLDER_MIME_TYPE);

        if (folderIdParent != null) {
            List<String> parents = List.of(folderIdParent);

            fileMetadata.setParents(parents);
        }
        try {
            GoogleDriveUtil.getDriveService().files().create(fileMetadata).setFields("id, name").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createGoogleFile(String googleFolderIdParent, String fileName, String customFileName){
        File fileMetadata = new File();
        List<String> parents = List.of(googleFolderIdParent);
        fileMetadata.setParents(parents);
        fileMetadata.setName(customFileName);
        fileMetadata.setMimeType(EXCEL_MIME_TYPE);

        java.io.File filePath = new java.io.File(SHEETS_FOLDER + fileName);

        FileContent mediaContent = new FileContent(EXCEL_MIME_TYPE, filePath);

        try {
            GoogleDriveUtil.getDriveService().files().create(fileMetadata, mediaContent).setFields("id").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
