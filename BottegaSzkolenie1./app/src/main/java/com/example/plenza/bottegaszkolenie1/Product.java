package com.example.plenza.bottegaszkolenie1;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private boolean isChecked;

    public Product(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
