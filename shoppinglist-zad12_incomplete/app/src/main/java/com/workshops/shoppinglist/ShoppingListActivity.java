package com.workshops.shoppinglist;

import android.app.Activity;
import android.os.Bundle;

public class ShoppingListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ProductListFragment())
                    .commit();
        }
    }

}
