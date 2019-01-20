package com.example.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int questionNumber = 5;
    private static int answersPerQuestion = 4;

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
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    Game game;
    Button goButton, b1, b2, b3, b4;
    TextView counterTextView, taskTextView, resultTextView;

    class Game {
        private boolean isGameActive = true;
        private int currentTurn = 0;
        private int secondsPerTurn = 15;
        private int correctlyAnsweredQuestionsNumber = 0;

        private ArrayList<Question> questions;
        private int questionsNumber;

        //Constructor
        public Game(int questionsNumber) {
            this.isGameActive = true;
            this.questionsNumber = questionsNumber;
            this.currentTurn = 0;
            questions = new ArrayList<Question>();
            for (int i = 0; i < this.questionsNumber; i++) {
                questions.add(new Question(answersPerQuestion));
            }
            goButton.setVisibility(View.INVISIBLE);

            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            b4.setVisibility(View.VISIBLE);

            counterTextView.setVisibility(View.VISIBLE);
            taskTextView.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.VISIBLE);

            b1.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[0]));
            b2.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[1]));
            b3.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[2]));
            b4.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[3]));

            counterTextView.setText(String.valueOf(secondsPerTurn)+"s");
            taskTextView.setText(questions.get(currentTurn).getTaskText());
            resultTextView.setText(this.getCurrentScore());
        }

        //returns a string with current score
        public String getCurrentScore() {
            return String.valueOf(correctlyAnsweredQuestionsNumber) + "/" + String.valueOf(questionsNumber);
        }

        public boolean isAnswerCorrect(int tag){
            return questions.get(this.currentTurn).isCorrect(tag);
        }

        public void nextTurn(){
            currentTurn++;
            if (currentTurn == questionNumber){
                restartGame();
                return;
            }
            b1.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[0]));
            b2.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[1]));
            b3.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[2]));
            b4.setText(String.valueOf(questions.get(currentTurn).getPossibleAnswers()[3]));

            counterTextView.setText(String.valueOf(secondsPerTurn)+"s");
            taskTextView.setText(questions.get(currentTurn).getTaskText());
        }

        public void addToCorrect(){
            correctlyAnsweredQuestionsNumber++;
            resultTextView.setText(this.getCurrentScore());
        }

        public void restartGame(){
            goButton.setVisibility(View.VISIBLE);

            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);

            counterTextView.setVisibility(View.INVISIBLE);
            taskTextView.setVisibility(View.INVISIBLE);
            resultTextView.setVisibility(View.INVISIBLE);
        }

    }

    class Question {
        private int answersNum = 4;
        private int min = 1, max = 100;
        private int a, b, answerVal, answerPos;
        private String operationType = "+";
        private String question;
        int[] answers;

        public String getTaskText(){
            return String.valueOf(a) + " " + operationType + " " + String.valueOf(b);
        }

        public int getAnswerVal() {
            return answerVal;
        }

        public int[] getPossibleAnswers(){
            return answers;
        }

        //constructor, answerNum - number of randomly generated answers
        public Question(int answersNum) {
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
        public boolean isCorrect(int posNumber) {
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

        goButton.setVisibility(View.VISIBLE);

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);

        counterTextView.setVisibility(View.INVISIBLE);
        taskTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = new Game(questionNumber);
            }
        });
    }

    public void onClick(View view){
        if (game.isAnswerCorrect(Integer.valueOf(String.valueOf(view.getTag())))){
            game.addToCorrect();
        }

        game.nextTurn();
    }

}
