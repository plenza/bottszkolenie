package com.workshops.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    public static Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private long id;
    private String productName;
    private boolean bought;

    public Product(String product) {
        this.productName = product;
        this.bought = false;
    }

    public Product(long id, String productName, boolean bought) {
        this.id = id;
        this.productName = productName;
        this.bought = bought;
    }

    public Product(Parcel source) {
        this.productName = source.readString();
        this.bought = source.readByte() != 0;
        this.id = source.readLong();
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isBought() {
        return bought;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeByte((byte) (bought ? 1 : 0));
        dest.writeLong(id);
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
