package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int questionsNumber = 10;
    private static int answersPerQuestion = 4;
    private static int secondsPerTurn = 5;
    private static long countDownInterval = 1000;

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
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

    void setViewsVisibility(int goButtonVisibility, int otherViewsVisibility) {
        goButton.setVisibility(goButtonVisibility);

        b1.setVisibility(otherViewsVisibility);
        b2.setVisibility(otherViewsVisibility);
        b3.setVisibility(otherViewsVisibility);
        b4.setVisibility(otherViewsVisibility);

        counterTextView.setVisibility(otherViewsVisibility);
        taskTextView.setVisibility(otherViewsVisibility);
        resultTextView.setVisibility(otherViewsVisibility);
    }

    Game game;
    Button goButton, b1, b2, b3, b4;
    TextView counterTextView, taskTextView, resultTextView, msgTextView;
    CountDownTimer countDownTimer;

    class Game {
        private int currentTurn;
        private int correctlyAnsweredQuestionsNumber;
        private ArrayList<Question> questions;
        private boolean isTimerActive;

        //Constructor
        Game(int questionsNumber) {
            this.isTimerActive = false;
            this.currentTurn = 0;
            this.correctlyAnsweredQuestionsNumber = 0;
            questions = new ArrayList<>();
            for (int i = 0; i < questionsNumber; i++) {
                questions.add(new Question(answersPerQuestion));
            }
            msgTextView.setText("");
            setViewsVisibility(View.INVISIBLE, View.VISIBLE);
            setText();
            startTimer();
        }

        private void startTimer(){
            cancelTimer();
            //Start the timer
            countDownTimer = new CountDownTimer(secondsPerTurn * 1000, countDownInterval) {
                public void onTick(long millisecondsUntilDone) {
                    Log.i("Seconds left!", String.valueOf(millisecondsUntilDone / 1000));
                    counterTextView.setText(String.valueOf(millisecondsUntilDone / 1000)+"s");
                }

                public void onFinish() {
                    Log.i("We're done!", "No more countdown");
                    nextTurn();
                }
            }.start();
            this.isTimerActive = true;
        }

        private void cancelTimer(){
            if (this.isTimerActive) {
                countDownTimer.cancel();
                isTimerActive = false;
            }
        }

        private void setText() {
            b1.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[0]));
            b2.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[1]));
            b3.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[2]));
            b4.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[3]));

            counterTextView.setText(String.valueOf(secondsPerTurn)+"s");
            taskTextView.setText(questions.get(currentTurn).getTaskText());
            resultTextView.setText(this.getCurrentScore());
        }

        //returns a string with current score
        String getCurrentScore() {
            return String.valueOf(correctlyAnsweredQuestionsNumber) + "/" + String.valueOf(questionsNumber);
        }

        boolean isAnswerCorrect(int tag){
            return questions.get(this.currentTurn).isCorrect(tag);
        }

        void nextTurn(){
            currentTurn++;
            if (currentTurn == questionsNumber){
                setViewsVisibility(View.VISIBLE, View.INVISIBLE);
                cancelTimer();
                Toast.makeText(getApplicationContext(), "Your result is " + getCurrentScore(), Toast.LENGTH_LONG).show();
                msgTextView.setText(getCurrentScore());
                msgTextView.animate().alpha(1).setDuration(2000);
                return;
            }
            setText();
            startTimer();
        }

        void addToCorrect(){
            correctlyAnsweredQuestionsNumber++;
            resultTextView.setText(this.getCurrentScore());
        }

    }

    class Question {
        private int min = 1, max = 100;
        private int a, b, answerVal, answerPos;
        private String operationType = "+";
        private String question;
        int[] answers;

        String getTaskText(){
            return question;
        }

        int[] getPossibleAnswers(){
            return answers;
        }

        //constructor, answerNum - number of randomly generated answers
        Question(int answersNum) {
            answers = new int[answersNum];
            a = randInt(min, max);
            b = randInt(min, max);
            question = String.valueOf(a) + " " + operationType + " " + String.valueOf(b);
            answerVal = a + b;
            answerPos = randInt(0, answersNum - 1);
            for (int i = 0; i < answersNum; i++) {
                if (i != answerPos) {
                    while (answers[i] == 0 || answers[i] == answerVal) {
                        answers[i] = randInt(min, 2 * max);
                    }
                } else {
                    answers[i] = answerVal;
                }
            }
        }

        //check whether the answer in a specific position (posNumber) is correct
        boolean isCorrect(int posNumber) {
            return posNumber == answerPos;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);

        b1 = findViewById(R.id.A1Button);
        b2 = findViewById(R.id.A2Button);
        b3 = findViewById(R.id.A3Button);
        b4 = findViewById(R.id.A4Button);

        counterTextView = findViewById(R.id.counterTextView);
        taskTextView = findViewById(R.id.taskTextView);
        resultTextView = findViewById(R.id.resultTextView);

        msgTextView = (TextView) findViewById(R.id.msgTextView);

        setViewsVisibility(View.VISIBLE, View.INVISIBLE);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = new Game(questionsNumber);
            }
        });
    }

    public void onClick(View view) {
        if (game.isAnswerCorrect(Integer.valueOf(String.valueOf(view.getTag())))){
            game.addToCorrect();
            msgTextView.setText("Correct");
        } else{
            msgTextView.setText("Incorrect");
        }
        msgTextView.setAlpha(1);
        msgTextView.animate().alpha(0).setDuration(2000);
        game.nextTurn();
    }

}
