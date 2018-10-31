package com.example.keren.multiplechoicequiz;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity {
    private List<Flashcards> flashcardsList;
    private int flashcardCountTotal;
    private int flashcardCounter;
    private Flashcards currentFlashcard;

    //Defining widgets
    TextView topBarTitle;
    TextView flashQuestionNum;
    TextView doubleTapPrompt;
    Button nxtButton;
    TextView flashquestion;
    TextView flashAnswerTitle;
    TextView flashAnswerContent;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

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
                            Intent homepage_intent = new Intent(FlashcardActivity.this, user_homepage.class);
                            startActivity(homepage_intent);
                        } else if (id == R.id.nav_logout){
                            Intent logout_intent = new Intent(FlashcardActivity.this, user_login.class);
                            startActivity(logout_intent);
                        }



                        return true;
                    }
                });


        topBarTitle = findViewById(R.id.topbar_title);
        flashQuestionNum = findViewById(R.id.question_num);
        doubleTapPrompt = findViewById(R.id.prompt_doubletap);
        nxtButton = findViewById(R.id.next_btn);
        flashquestion = findViewById(R.id.flash_question);
        flashAnswerTitle = findViewById(R.id.flash_answer_title);
        flashAnswerContent = findViewById(R.id.flash_answer_content);

        // Calling the database class, retrieving database questions, and shuffling questions
        QuizDatabase dbHelper = new QuizDatabase(this);
        flashcardsList = dbHelper.getAllFlashcards();
        Collections.shuffle(flashcardsList);

        // Retrieved total number of questions by using .size() of flashcardList
        flashcardCountTotal = flashcardsList.size();

        showNextFlashcard();

        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextFlashcard();
                //nxtButton.setText("registered");
            }
        });

    }

    private void showNextFlashcard(){
        //Making sure the next sets of questions and answers are displayed
        if (flashcardCounter < flashcardCountTotal) {
            currentFlashcard = flashcardsList.get(flashcardCounter);

            flashquestion.setText(currentFlashcard.getQuestionF());
            flashAnswerTitle.setText(currentFlashcard.getAnswerF());
            flashAnswerContent.setText(currentFlashcard.getAnswerF());

            nxtButton.setText("Next");
            flashcardCounter++;

        } else {
            nxtButton.setText("Finish");
            finish();
        }
    }
}
