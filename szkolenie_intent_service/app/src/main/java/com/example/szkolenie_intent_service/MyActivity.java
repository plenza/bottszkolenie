package com.example.szkolenie_intent_service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyActivity extends Activity {

    private TextView fileContentView;
    private EditText urlInput;
    private Button goButton;
    private FileDownloadedReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fileContentView = (TextView) findViewById(R.id.content);
        urlInput = (EditText) findViewById(R.id.url_input);
        goButton = (Button) findViewById(R.id.go_button);
        goButton.setOnClickListener(v -> downloadFile(urlInput.getText().toString()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new FileDownloadedReceiver();
        registerReceiver(receiver, new IntentFilter(FileDownloaderService.ACTION_FILE_DOWNLOADED));

        int x = 52;
        runOnUiThread(() -> Log.d("MainActivity", "Hello from lambda! x = " + x));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void downloadFile(final String text) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                String filePath = new FileDownloader(MyActivity.this).downloadFile(text);
                return filePath;
            }

            @Override
            protected void onPostExecute(String filePath) {
                super.onPostExecute(filePath);
                readFileAndDisplayContent(filePath);
            }
        }.execute(text);


//        Intent intent = new Intent(this, FileDownloaderService.class);
//        intent.putExtra(FileDownloaderService.FILE_URL, text);
//        startService(intent);
    }

    /**
     * Otwiera plik, którego nazwa zwracana jest przez FileDownloader
     *
     * @param fileName
     */
    private void readFileAndDisplayContent(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();
            fileContentView.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class FileDownloadedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String filePath = intent.getStringExtra(FileDownloaderService.FILE_PATH);
            readFileAndDisplayContent(filePath);
        }
    }

}
