package com.example.gridlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> competitionsIDs = new LinkedList<String>();
        List<String> marks = new LinkedList<String>();
        List<String> numOfQuestions = new LinkedList<String>();

        competitionsIDs.add("cID1");
        competitionsIDs.add("cID2");
        competitionsIDs.add("cID3");

        marks.add("m1");
        marks.add("m2");
        marks.add("m3");

        numOfQuestions.add("noQ1");
        numOfQuestions.add("noQ2");
        numOfQuestions.add("noQ3");

        //make sure that the lists contain data or else display will be blank screen

        TableRow.LayoutParams params1 = new TableRow.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(GridLayout.LayoutParams.FILL_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
        TableLayout tbl = (TableLayout) findViewById(R.id.tableLayout);
        for (int ctr = 0; ctr < marks.size(); ctr++) {
            //Creating new tablerows and textviews
            TableRow row = new TableRow(this);
            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);
            TextView txt3 = new TextView(this);
            //setting the text
            txt1.setText(competitionsIDs.get(ctr));
            txt2.setText(marks.get(ctr));
            txt3.setText(numOfQuestions.get(ctr));
            txt1.setLayoutParams(params1);
            txt2.setLayoutParams(params1);
            txt3.setLayoutParams(params1);
            //the textviews have to be added to the row created
            row.addView(txt1);
            row.addView(txt2);
            row.addView(txt3);
            row.setLayoutParams(params2);
            tbl.addView(row);
        }
    }
}
