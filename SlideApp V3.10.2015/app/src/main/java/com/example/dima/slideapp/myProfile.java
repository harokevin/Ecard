package com.example.dima.slideapp;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.BatchUpdateException;


public class myProfile extends ActionBarActivity {
    DataBase DATABASE;
    String[] CONTACTS = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        openDATABASE();
        Cursor cursor = DATABASE.getAllRows();

        //chaning the button to go back to the MAIN MENU
        Button backToPeopleHide = (Button)findViewById(R.id.backToMyPeople);
        backToPeopleHide.setText("Main Menu");
        backToPeopleHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });

        int i =0;

        if (cursor.moveToFirst()) {
            do {
                String contactName = cursor.getString(DATABASE.COL_NAME);
                String contactPassword = cursor.getString(DATABASE.COL_PASSWORD);
                String contactPhonenumber = cursor.getString(DATABASE.COL_PHONENUMBER);
                String contactSnapchat = cursor.getString(DATABASE.COL_SNAPCHAT);
                String contactInstagram = cursor.getString(DATABASE.COL_INSTAGRAM);
                String contactFacebook = cursor.getString(DATABASE.COL_FACEBOOK);
                String contactEmail = cursor.getString(DATABASE.COL_EMAIL);
                CONTACTS[i] = contactName + "," + contactPassword + "," + contactPhonenumber + "," + contactSnapchat+
                        ","+ contactInstagram + "," + contactFacebook + "," + contactEmail;
                i++;
            } while (cursor.moveToNext());
        }//closing if statement

        final String[] MAIN_USER = CONTACTS[0].split(",");
        iconCheck(MAIN_USER);//checking icons
                TextView contactName = (TextView) findViewById(R.id.contactNameMyProfile);
                contactName.setText(MAIN_USER[0]);


        cursor.close();
        //button for setUpAccount
        Button setUp = (Button) findViewById(R.id.setUpAccountButton);
        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), setUpAccount.class);
                startActivity(intent);
            }
        });
        final TextView showInfoTextView = (TextView)findViewById(R.id.showInformationTextView);
        //image buttons to display corresponding information in the text view on the bottom
        ImageButton phoneImage = (ImageButton)findViewById(R.id.imageButtonPHONENUMBER);
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(MAIN_USER[3]);
            }
        });
        ImageButton snapchatImage =(ImageButton)findViewById(R.id.imageButtonSNAPCHAT);
        snapchatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(MAIN_USER[2]);
            }
        });
        ImageButton instagramImage = (ImageButton)findViewById(R.id.imageButtonINSTAGRAM);
        instagramImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(MAIN_USER[4]);
            }
        });
        ImageButton facebookImage = (ImageButton)findViewById(R.id.imageButtonFACEBOOK);
        facebookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(MAIN_USER[5]);
            }
        });
        ImageButton emailImage = (ImageButton)findViewById(R.id.imageButtonEMAIL);
        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(MAIN_USER[6]);
            }
        });


    }//closing onCreate()


    //a method to check which icons are disabled
    public void iconCheck(String[] A){
        //checking what icons have been checked during ICONS_FOR_REGISTER activity
        ////////first if statement will check if PHONENUMBER icon has been clicked or not
        //TextView textView =(TextView) findViewById(R.id.showInformationTextView);
        if(A[3].equals("null")){
            ImageButton image = (ImageButton) findViewById(R.id.imageButtonPHONENUMBER);
            image.setVisibility(View.GONE);
        }//cloosing the first if-else statement

        if(A[2].equals("null")){
            //this means SNAPCHAT icon has NOT been clicked and we need to diasble it
            ImageButton image =(ImageButton)findViewById(R.id.imageButtonSNAPCHAT);
            image.setVisibility(View.GONE);
        }//closing the second if statement

        if(A[4].equals("null")){
            //hiding the icon for INSTAGRAM
            ImageButton image =(ImageButton) findViewById(R.id.imageButtonINSTAGRAM);
            image.setVisibility(View.GONE);
        }//closing the 3rd if statement

        if(A[5].equals("null")){
            //diasbiling the icon and image view for FACEBOOK
            ImageButton image = (ImageButton)findViewById(R.id.imageButtonFACEBOOK);
            image.setVisibility(View.GONE);
        }//closing 4th if statement

        if(A[6].equals("null")){
            //hidding EMAIL
            ImageButton image =(ImageButton)findViewById(R.id.imageButtonEMAIL);
            image.setVisibility(View.GONE);
        }//closing the 5th if statement
    }//closing iconCheck()



    @Override
    protected void onDestroy(){
        super.onDestroy();;
        closeDATABASE();
    }

    private void closeDATABASE() {
        DATABASE.close();
    }//closing closeDATABASE()

    private void openDATABASE() {
        DATABASE = new DataBase(this);
        DATABASE.open();
    }//closing openDATABASE()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
