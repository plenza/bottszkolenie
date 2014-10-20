package com.workshops.shoppinglist.storage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.workshops.shoppinglist.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDbOpenHelper extends SQLiteOpenHelper {

    public static final String PRODUCTS_TABLE_NAME = "products";
    public static final String PRODUCTS_PRODUCT_NAME = "productName";
    public static final String PRODUCTS_IS_BOUGHT = "isBought";
    public static final String PRODUCTS_ID = "_id";

    public ProductDbOpenHelper(Context context) {
        super(context, PRODUCTS_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PRODUCTS_TABLE_NAME + " (" +
                        PRODUCTS_ID + " INTEGER PRIMARY KEY," +
                        PRODUCTS_PRODUCT_NAME + " TEXT NOT NULL," +
                        PRODUCTS_IS_BOUGHT + " INTEGER NOT NULL" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME + ";");
    }

    public List<Product> getAllProducts() {
        Cursor fields = getReadableDatabase().rawQuery("SELECT * FROM " + PRODUCTS_TABLE_NAME + ";", null);
        List<Product> products = new ArrayList<Product>();
        fields.move(-1);
        while (fields.moveToNext()) {
            Product product = createProductFromRow(fields);
            products.add(product);
        }
        return products;
    }

    private Product createProductFromRow(Cursor fields) {
        return new Product(fields.getInt(0), fields.getString(1), intToBool(fields.getInt(2)));
    }

    private boolean intToBool(int anInt) {
        return anInt > 0;
    }

    public void removeAllBoughtProducts() {
        getWritableDatabase().execSQL("DELETE FROM " + PRODUCTS_TABLE_NAME + ";");
    }

    public void addNewProduct(String productName) {
        ContentValues newProduct = new ContentValues();
        newProduct.put(PRODUCTS_PRODUCT_NAME, productName);
        newProduct.put(PRODUCTS_IS_BOUGHT, 0);
        getWritableDatabase().insert(PRODUCTS_TABLE_NAME, null, newProduct);
    }

    public void setProductBought(long id, boolean bought) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUCTS_IS_BOUGHT, bought ? "1" : "0");
        getWritableDatabase().update(PRODUCTS_TABLE_NAME, cv, PRODUCTS_ID + " = ?", new String[]{String.valueOf(id)});
    }
}

