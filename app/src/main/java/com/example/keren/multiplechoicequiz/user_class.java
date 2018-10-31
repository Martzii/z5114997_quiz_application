package com.example.keren.multiplechoicequiz;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class user_class extends AppCompatActivity {

    private Button module_btn;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_class);

        //Navigation menu
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
                            Intent x = new Intent(user_class.this, user_homepage.class);
                            startActivity(x);
                        } else if (id == R.id.nav_logout){
                            Intent logout_intent = new Intent(user_class.this, user_login.class);
                            startActivity(logout_intent);
                        }



                        return true;
                    }
                });

        //button
        Button module_btn = findViewById(R.id.module_btn);
        module_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_class.this, user_module.class);
                startActivity(intent);
            }
        });
    }
}
