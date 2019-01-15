package com.example.dmitrij.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red, 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    public void playAgain(View view){
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int y = 0; y < gridLayout.getChildCount(); y++){
            ImageView counter = (ImageView) gridLayout.getChildAt(y);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        gameActive = true;
    }

    public void dropping(View view) {
        ImageView counter = (ImageView) view;
        int tagCounter = Integer.parseInt(counter.getTag().toString());
        Log.i("tag", String.valueOf(tagCounter));
        if (gameState[tagCounter] == 2 && gameActive ) {

            gameState[tagCounter] = activePlayer;

            counter.setTranslationY(-1000);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellowpie);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.redpie);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).setDuration(500);

            for (int[] winningPosition : winningPositions)
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[0]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = "Red";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    }
                    // someone has won
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_LONG).show();
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
