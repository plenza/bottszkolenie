package com.example.plenza.bottegaszkolenie3;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class MyActivity extends Activity {

    public static final String CHECK_BOX_STATE = "CheckBoxState";
    public static final String FILE_DOWNLOADED_EVENT = "FILE_DOWNLOADED_EVENT";
    CheckBox checkBox;
    Button button;
    private Receive receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        checkBox = (CheckBox)findViewById(R.id.checkBox);

        SharedPreferences sp = getSharedPreferences("MyAppSp", 0);
        checkBox.setChecked(sp.getBoolean(CHECK_BOX_STATE, false));

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MyActivity.this, MyService.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new Receive();
        registerReceiver(receiver, new IntentFilter(FILE_DOWNLOADED_EVENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCheckBoxClick(View view) {
        SharedPreferences sp = getSharedPreferences("MyAppSp", 0);
        sp.edit().putBoolean(CHECK_BOX_STATE, checkBox.isChecked()).commit();
    }

    class Receive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }

    static class AsyncDownload extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
