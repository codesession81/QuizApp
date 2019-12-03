package com.example.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingScreenActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighScore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }


    private void startQuiz(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
        Die Score, die von der QuizActivity zurückgegeben wurde, in der Variable score speichern und der Methode
        updateHighscore übergeben
         */
        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score>highscore){
                    updateHighscore(score);
                }
            }
        }
    }



    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Highscore: "+highscore);
    }

    private void updateHighscore(int highScoreNew){
            highscore = highScoreNew;
            textViewHighscore.setText("Highscore: "+highscore);

            /*
            Die erhaltene Score dauerhaft speichern
             */
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putInt(KEY_HIGHSCORE,highscore);
            editor.apply();
    }
}
