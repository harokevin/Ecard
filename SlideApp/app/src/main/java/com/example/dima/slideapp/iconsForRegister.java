package com.example.dima.slideapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class iconsForRegister extends ActionBarActivity {
    //fields for buttons clicked, going to be passed to the REGISTER ACTIVITY
    int[] SELECTED_ICONS = new int[5];
    String USERNAME;
    String PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons_for_register);

        //gettint USERNAME AND PASSWORD FROM PREVIOUES ACTIVITY
        Intent extrasIntent = getIntent();
        final String userName = extrasIntent.getStringExtra("String");
            USERNAME = userName;
        final String password = extrasIntent.getStringExtra("Password");
            PASSWORD = password;

        //populating the array with all zeroes
        for(int i=0; i<5; i++){
            SELECTED_ICONS[i] = 0;
        }//clossing for-loop

        //setting button listeners for icon buttons
        //phone Button
        final Button phoneButton = (Button) findViewById(R.id.phoneIconButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneButton.getBackground().setAlpha(128);
                //setting the icon for 1
                SELECTED_ICONS[0] = 1;
            }
        });
        //snapChat Button
        final Button snapchatButton = (Button) findViewById(R.id.snapchatIConButton);
        snapchatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snapchatButton.getBackground().setAlpha(128);
                SELECTED_ICONS[1] =1;
            }
        });
        //instagram Button
        final Button instagramButton = (Button) findViewById(R.id.instagramButton);
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instagramButton.getBackground().setAlpha(128);
                SELECTED_ICONS[2] =1;
            }
        });
        //facebook button
        final Button facebookButton = (Button) findViewById(R.id.facebookIconButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookButton.getBackground().setAlpha(128);
                SELECTED_ICONS[3] =1;
            }
        });
        //email Button
        final Button emailButton = (Button) findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButton.getBackground().setAlpha(128);
                SELECTED_ICONS[4] =1;
            }
        });


        //setting a button onClick Listerner for CONTINUE BUTTON
        Button CONTINUE_BUTTON = (Button) findViewById(R.id.iconsForRegisterButton);
        CONTINUE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting the REGISTER activity
                Intent intent = new Intent(getApplicationContext(), Register.class);
                //passing an array with selected icons marked 1 to REGISTER activity
                intent.putExtra("SELECTED_ICONS", SELECTED_ICONS);
                intent.putExtra("USERNAME", USERNAME);
                intent.putExtra("PASSWORD", PASSWORD);
                startActivity(intent);
            }
        });

    }//closing onCreate()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_icons_for_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
