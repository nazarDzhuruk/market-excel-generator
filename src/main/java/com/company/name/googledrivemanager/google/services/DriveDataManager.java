package com.company.name.googledrivemanager.google.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class DriveDataManager {

    public static List<File> getGoogleFilesByName(String fileNameLike) throws IOException {

        String pageToken = null;
        List<File> files = new ArrayList<>();
        Drive driveService = GoogleDriveUtil.getDriveService();

        String query = " name contains '" + fileNameLike + "' "
                + " and mimeType != 'application/vnd.google-apps.folder' ";
        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")
                    .setPageToken(pageToken).execute();
            files.addAll(result.getFiles());
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        return files;
    }

    public static List<File> getGoogleSubFolderByName(String googleFolderIdParent, String subFolderName)
            throws IOException {
        Drive driveService = GoogleDriveUtil.getDriveService();

        String query;
        String pageToken = null;
        List<File> list = new ArrayList<>();

        if (googleFolderIdParent == null) {
            query = " name = '" + subFolderName + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and 'root' in parents";
        } else {
            query = " name = '" + subFolderName + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and '" + googleFolderIdParent + "' in parents";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, createdTime)")
                    .setPageToken(pageToken).execute();
            list.addAll(result.getFiles());
            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        return list;
    }

    public static List<File> getGoogleRootFoldersByName(String subFolderName) throws IOException {
        return getGoogleSubFolderByName(null, subFolderName);
    }
}