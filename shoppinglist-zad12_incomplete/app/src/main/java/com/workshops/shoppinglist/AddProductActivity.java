package com.workshops.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends Activity {

    public static final String RESULT_KEY_PRODUCT_NAME = "product_name";

    private Button addButton;
    private EditText productInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);
        productInput = (EditText) findViewById(R.id.product_input);
        addButton = (Button) findViewById(R.id.product_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnSpecifiedProduct();
            }
        });
        productInput.addTextChangedListener(new DisableButtonOnEmptyText());
    }

    private void returnSpecifiedProduct() {
        Intent productData = new Intent().putExtra(RESULT_KEY_PRODUCT_NAME, productInput.getText().toString());
        setResult(Activity.RESULT_OK, productData);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private class DisableButtonOnEmptyText implements TextWatcher {

        private DisableButtonOnEmptyText() {
            addButton.setEnabled(false);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            addButton.setEnabled(s.length() != 0);
        }
    }
}
