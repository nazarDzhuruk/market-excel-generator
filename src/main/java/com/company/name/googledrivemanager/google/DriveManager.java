package com.company.name.googledrivemanager.google;

import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.List;

public interface DriveManager {
    void deleteFileByName(String fileName);
    void deleteFolderByName(String folderName);
    void uploadGoogleFile(String folderName, String fileName, String customFileName);
    List<File> getGoogleFileByName(String fileNameLike);
}
