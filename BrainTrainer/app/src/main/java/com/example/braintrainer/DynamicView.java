package com.example.braintrainer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicView {
    Context context;

    public DynamicView(Context context) {
        this.context = context;
    }

    public TextView addTextView(Context context, String textViewDescription){
        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(14);
        textView.setTextColor(Color.parseColor("#FF6B9EFD"));
        textView.setText("Hello 1: "+ textViewDescription);
        textView.setMaxEms(8);
        return textView;
    }
}
