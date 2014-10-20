package com.example.plenza.bottegaszkolenie1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewProductActivity extends Activity {

    EditText productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        productName = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_product, menu);
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

    public void onButtonClick(View view) {
        Intent result = new Intent();
        result.putExtra("NEW_PRODUCT", new Product(productName.getText().toString(), false));
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void onBrowserButtonClick(View view) {
        Intent brIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.pl"));
        startActivity(brIntent);
    }

    public void onLocationButtonClick(View view) {
        Intent brIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.6,-122.3"));
        startActivity(brIntent);
    }
}
