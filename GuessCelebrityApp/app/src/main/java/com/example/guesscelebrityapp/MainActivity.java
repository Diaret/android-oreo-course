package com.example.guesscelebrityapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Document document = null;
    private ArrayList<Celebrity> celebrities;
    Button b1, b2, b3, b4;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrities = new ArrayList<Celebrity>();

        DownloadCelebrities downloadCelebrities = new DownloadCelebrities();
        try {
            Object object = downloadCelebrities.execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);

        imageView = findViewById(R.id.imageView);

        nextTurn();
    }

    public void clickButton(View view){
        Button b = (Button) view;
        if (b.getText() == (String) imageView.getTag()){
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
        }   else{
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_LONG).show();
        }
        nextTurn();
    }

    private void nextTurn(){
        Celebrity cBase = celebrities.get(randInt(0,celebrities.size()-1));
        Picasso.get().load(cBase.getImgSrc()).into(imageView);
        imageView.setTag(cBase.getName());
        int iBase = randInt(1,4);
        int id = getResources().getIdentifier("button"+iBase, "id", getPackageName());
        Button bCurrent = (Button) findViewById(id);
        bCurrent.setText(cBase.getName());

        for (int j = 1; j <5; j++){
            if (j != iBase){
                Celebrity cCurrent = celebrities.get(randInt(0,celebrities.size()-1));
                if (cCurrent.getName() == cBase.getName()){
                    continue;
                } else{
                    id = getResources().getIdentifier("button"+j, "id", getPackageName());
                    bCurrent = (Button) findViewById(id);
                    bCurrent.setText(cCurrent.getName());
                }
            }
        }

    }

    public class DownloadCelebrities extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try{
                document = Jsoup.connect("http://www.posh24.se/kandisar").get();
                Elements links = document.select("div.channelListEntry");

                for (Element e: links
                ) {
                    celebrities.add(new Celebrity(e.getElementsByClass("name").first().html(), e.getElementsByTag("img").attr("src")));
                }

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }

}
