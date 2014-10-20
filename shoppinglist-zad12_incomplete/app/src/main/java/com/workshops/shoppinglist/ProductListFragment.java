package com.workshops.shoppinglist;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import com.workshops.shoppinglist.storage.ProductDbOpenHelper;

public class ProductListFragment extends ListFragment {

    public static final int ADD_PRODUCT_REQUEST_CODE = 0;

    private ProductListAdapter arrayAdapter;
    private ProductDbOpenHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
        databaseHelper = new ProductDbOpenHelper(getActivity());

        populateListFromDatabase();
    }

    private void populateListFromDatabase() {
        arrayAdapter = new ProductListAdapter(getActivity(), databaseHelper.getAllProducts(), databaseHelper);
        setListAdapter(arrayAdapter);
    }

    private void removeBoughtItems() {
        databaseHelper.removeAllBoughtProducts();
        populateListFromDatabase();
    }

    private void openAddProductActivity() {
        Intent addProductActivityIntent = new Intent(getActivity(), AddProductActivity.class);
        startActivityForResult(addProductActivityIntent, ADD_PRODUCT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PRODUCT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            addProduct(data.getStringExtra(AddProductActivity.RESULT_KEY_PRODUCT_NAME));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addProduct(String productName) {
        databaseHelper.addNewProduct(productName);
        populateListFromDatabase();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_product) {
            openAddProductActivity();
            return true;
        } else if (item.getItemId() == R.id.menu_item_remove_bought) {
            removeBoughtItems();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
