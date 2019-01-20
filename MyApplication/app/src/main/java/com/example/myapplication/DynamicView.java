package com.example.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicView {
    Context context;

    public DynamicView(Context context) {
        this.context = context;
    }

    public TextView addTextView(Context context, String textViewDescription){
        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100,50);
        layoutParams.height = 50;
        layoutParams.width = 100;

        final TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(12);
        textView.setTextColor(Color.parseColor("#FF6B9EFD"));
        textView.setText(textViewDescription);
        //textView.setPadding(8,8,8,8);
        //textView.setWidth(100);
        //textView.setMaxEms(100);
        return textView;
    }
}
