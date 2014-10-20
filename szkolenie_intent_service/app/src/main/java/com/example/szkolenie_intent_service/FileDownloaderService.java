package com.example.szkolenie_intent_service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

/**
 * Created by plenza on 9/24/14.
 */
public class FileDownloaderService extends IntentService {

    public static final String ACTION_FILE_DOWNLOADED = "ACTION_FILE_DOWNLOADED";
    public static final String FILE_URL = "FileUrl";
    public static final String FILE_PATH = "FilePath";
    private NotificationManager systemService;

    public FileDownloaderService() {
        super("FileDownloaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String urlToFile = intent.getStringExtra(FILE_URL);
        String filePath = new FileDownloader(this).downloadFile(urlToFile);
        Intent action_file_downloaded = new Intent(ACTION_FILE_DOWNLOADED);
        action_file_downloaded.putExtra(FILE_PATH, filePath);
        sendBroadcast(action_file_downloaded);

        Notification notification =  new Notification.Builder(this).setContentText("Bla bla").setSmallIcon(android.R.drawable.ic_lock_lock).setTicker("Sample").build();
        systemService.notify(5, notification);
    }
}
