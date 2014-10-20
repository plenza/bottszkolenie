package com.example.plenza.bottegaszkolenie1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by plenza on 9/23/14.
 */
public class MyArrayAdapter extends ArrayAdapter<Product> {

    LayoutInflater inflater = LayoutInflater.from(getContext());

    public MyArrayAdapter(Context context, List<Product> values) {
        super(context, R.layout.row_layout, values);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = inflater.inflate(R.layout.row_layout, parent, false);
        }
        TextView textView = (TextView) itemView.findViewById(R.id.rowTextView);
        CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.rowCheckBox);
        final Product product = getItem(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setChecked(isChecked);
            }
        });
        textView.setText(product.getName());
        checkBox.setChecked(product.isChecked());
        return itemView;
    }
}
