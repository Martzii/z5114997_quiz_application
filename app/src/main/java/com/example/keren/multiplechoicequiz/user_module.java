package com.example.keren.multiplechoicequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class user_module extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private TextView textViewAverage;

    private int highscore;
    private int averageScore;
    private List<Integer> scoreStore = new ArrayList<>(); {
    }

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_module);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        int id = menuItem.getItemId();

                        if(id == R.id.nav_home){
                            Intent x = new Intent(user_module.this, user_homepage.class);
                            startActivity(x);
                        } else if (id == R.id.nav_logout){
                            Intent logout_intent = new Intent(user_module.this, user_login.class);
                            startActivity(logout_intent);
                        }



                        return true;
                    }
                });

        textViewHighscore = findViewById(R.id.text_view_highscore);
        textViewAverage = findViewById(R.id.text_view_average);
        loadHighscore();
        averageScore = calculateAverage(scoreStore);
        textViewAverage.setText("");



        //Setting button that leads to a PDF of lecture slides for that particular module
        Button lecturebtn = findViewById(R.id.lecture);
        lecturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_module.this, LecturePDF.class);
                startActivity(intent);
            }
        });

        //Setting button that leads to curated list of video relating to that module
        Button videobtn = findViewById(R.id.video_btn);
        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_module.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });

        Button flashcard_btn = findViewById(R.id.start_flashcard_btn);
        flashcard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_module.this, FlashcardActivity.class);
                startActivity(intent);
            }
        });

        //Setting button that leads to quiz to test user knowledge of content relating to that module
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

    }

    private void startQuiz() {
        Intent intent = new Intent(user_module.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    //Retrieving quiz results and passing in the score to update the highscore if it is the highest score of their attempts
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                scoreStore.add(score);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
        calculateAverage(scoreStore);
    }

    //Loading the high score onto the screen
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    //updating the highscore and storing it into SharedPreferences
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }

    //Calculating the average score
    private int calculateAverage(List <Integer> scoreStore){
        int sum = 0;
        for(int i = 0;i < scoreStore.size();i++){
            sum = sum + scoreStore.get(i);
        }
        int totalAttempts = scoreStore.size() + 1;
        int average = sum/totalAttempts;

        return average;
    }

}

