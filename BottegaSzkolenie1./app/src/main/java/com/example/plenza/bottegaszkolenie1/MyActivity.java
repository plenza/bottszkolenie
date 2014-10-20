package com.example.plenza.bottegaszkolenie1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;


public class MyActivity extends Activity {

    public static final String PRODUCT_LIST_KEY = "ProductList";
    ArrayAdapter<Product> listAdapter;
    private ArrayList<Product> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ListView myList = (ListView) findViewById(R.id.myList);
        values = new ArrayList();
        values.add(new Product("Android", false));
        values.add(new Product("iPhone", false));
        values.add(new Product("Windows", false));

        for (int i = 0; i < 40; i++) {
            values.add(new Product(UUID.randomUUID().toString(), false));
        }

        listAdapter = new MyArrayAdapter(this, values);

        myList.setAdapter(listAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PRODUCT_LIST_KEY, values);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(PRODUCT_LIST_KEY)) {
            values = (ArrayList<Product>) savedInstanceState.getSerializable(PRODUCT_LIST_KEY);
            listAdapter.clear();
            listAdapter.addAll(values);
        }
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
        if(id == R.id.some_item) {
            addNewProductActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewProductActivity() {
        Intent intent = new Intent(this, NewProductActivity.class);
        //startActivityForResult(intent, 123);
        startActivity(intent);
    }

    public void onButtonClick(View view) {
        addNewProductActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int x;
        x =5;
        x++;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                addNewProduct((Product) data.getSerializableExtra("NEW_PRODUCT"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addNewProduct(Product newProduct) {
        listAdapter.add(newProduct);
    }
}
