package com.example.keren.multiplechoicequiz;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

public class LecturePDF extends AppCompatActivity {

    PDFView pdfView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_pdf);

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
                            Intent x = new Intent(LecturePDF.this, user_homepage.class);
                            startActivity(x);
                        } else if (id == R.id.nav_logout){
                            Intent logout_intent = new Intent(LecturePDF.this, user_login.class);
                            startActivity(logout_intent);
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        pdfView=(PDFView) findViewById(R.id.pdfView);

        pdfView.fromAsset("IntroNetworking.pdf").load();
    }
}
