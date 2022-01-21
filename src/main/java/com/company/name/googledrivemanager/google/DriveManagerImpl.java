package com.company.name.googledrivemanager.google;

import com.company.name.googledrivemanager.google.services.DriveDataBuilder;
import com.company.name.googledrivemanager.google.services.DriveDataManager;
import com.company.name.googledrivemanager.google.services.GoogleDriveUtil;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DriveManagerImpl implements DriveManager {
    private static final String EXTENSION = ".xlsx";
    private static final String DASH = "-";

    @Override
    public void deleteFileByName(String fileName) {
        try {
            String fileId = DriveDataManager.getGoogleFilesByName(fileName).stream()
                    .map(File::getId).collect(Collectors.joining());
            GoogleDriveUtil.getDriveService().files().delete(fileId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFolderByName(String folderName) {
        try {
            String folderId = DriveDataManager.getGoogleRootFoldersByName(folderName).stream()
                    .map(File::getId).collect(Collectors.joining());
            GoogleDriveUtil.getDriveService().files().delete(folderId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadGoogleFile(String folderName, String fileName, String customFileName) {
        try {
            if (DriveDataManager.getGoogleRootFoldersByName(folderName).isEmpty()) {
                DriveDataBuilder.createGoogleFolder(null, folderName);
                DriveDataBuilder
                        .createGoogleFile(googleFolderId(folderName), fileName + EXTENSION, customFileName);

            } else if (equalsName(fileName, googleFolderName(folderName))) {
                DriveDataBuilder
                        .createGoogleFile(googleFolderId(folderName), fileName + EXTENSION, customFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<File> getGoogleFileByName(String fileNameLike) {
        List<File> files = null;
        try {
            files = DriveDataManager.getGoogleFilesByName(fileNameLike);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    private boolean equalsName(String fileName, String folderName) {
        return IntStream.range(0, folderName.split(DASH).length)
                .mapToObj(i -> fileName.split(DASH)[i].equals(folderName.split(DASH)[i]))
                .allMatch(b -> b.equals(true));
    }

    private String googleFolderId(String folderName) {
        String googleFolderId = null;
        try {
            googleFolderId = DriveDataManager.getGoogleRootFoldersByName(folderName)
                    .stream().map(File::getId).toArray()[0].toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleFolderId;
    }

    private String googleFolderName(String folderName) {
        String googleFolderName = null;
        try {
            googleFolderName = DriveDataManager.getGoogleRootFoldersByName(folderName)
                    .stream().map(File::getName).toArray()[0].toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleFolderName;
    }
}