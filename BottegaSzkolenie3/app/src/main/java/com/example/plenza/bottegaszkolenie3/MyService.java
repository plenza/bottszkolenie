package com.example.plenza.bottegaszkolenie3;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by plenza on 9/24/14.
 */
public class MyService extends IntentService {


    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(MyActivity.FILE_DOWNLOADED_EVENT));
    }
}
