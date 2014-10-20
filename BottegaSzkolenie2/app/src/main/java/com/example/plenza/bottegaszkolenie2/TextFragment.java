package com.example.plenza.bottegaszkolenie2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    public static final String ARG_TEXT_TO_DISPLAY = "ARG_TEXT_TO_DISPLAY";
    private String textToDisplay;
    private TextView myTextView;

    public static TextFragment create(String textToDisplay) {
        TextFragment textFragment = new TextFragment();
        Bundle data = new Bundle();
        data.putString(ARG_TEXT_TO_DISPLAY, textToDisplay);
        textFragment.setArguments(data);
        return textFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToDisplay = getArguments().getString(ARG_TEXT_TO_DISPLAY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myTextView = (TextView) view.findViewById(R.id.text_to_display);
        myTextView.setText(textToDisplay);
    }
}
