package com.ishitadhoot.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    int locationOfCorrectAnswer;
    int score = 0;
    int numOfSums =0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumText;
    TextView timer;
    TextView scoreText;
    GridLayout layout;
    TextView result;

    public void playAgain(View view){
        clickFunction(goButton);
        score = 0;
        numOfSums =0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

    }

    public void clickFunction(View view){
        final Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        createQuestion ();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(((int) millisUntilFinished / 1000)+"s");

            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Your score was " + score + "/" + numOfSums);
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);


            }
        }.start();

    }

    public void chooseAnswer(View view){

        result = (TextView) findViewById(R.id.result);
        numOfSums++;
        TextView scoreText = (TextView) findViewById(R.id.score);

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            result.setText("Correct!");
            this.score++;
            scoreText.setText(this.score + "/" + numOfSums);
            createQuestion ();

        }
        else{
            result.setText("Incorrect!");
            scoreText.setText(this.score + "/" + numOfSums);
            createQuestion ();
        }

        result.setVisibility(View.VISIBLE);

    }

    public void createQuestion () {

        layout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        sumText.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        layout.animate().alpha(1f);
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumText.setText(a+" + "+b);
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i =0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.goButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        sumText = (TextView) findViewById(R.id.sum);
        timer = (TextView) findViewById(R.id.timer);
        scoreText = (TextView) findViewById(R.id.score);
        layout = (GridLayout) findViewById(R.id.gridLayout);



    }
}
