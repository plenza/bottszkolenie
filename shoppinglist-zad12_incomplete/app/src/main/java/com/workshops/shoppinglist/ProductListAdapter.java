package com.workshops.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.workshops.shoppinglist.storage.ProductDbOpenHelper;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private final LayoutInflater layoutInflater;
    private final ProductDbOpenHelper databaseHelper;

    public ProductListAdapter(Context context, List<Product> objects, ProductDbOpenHelper databaseHelper) {
        super(context, R.layout.product_list_row, objects);
        this.databaseHelper = databaseHelper;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View productListRow = convertView;
        if (productListRow == null) {
            productListRow = layoutInflater.inflate(R.layout.product_list_row, parent, false);
        }
        TextView productNameView = (TextView) productListRow.findViewById(R.id.product_name);
        CheckBox checkBox = (CheckBox) productListRow.findViewById(R.id.product_checkbox);

        final Product product = getItem(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setBought(isChecked);
                databaseHelper.setProductBought(product.getId(), isChecked);
            }
        });

        productNameView.setText(product.getProductName());
        checkBox.setChecked(product.isBought());

        return productListRow;
    }
}
