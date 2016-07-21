package com.example.dima.slideapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Contact extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        //getting an array from "myPeople" activity
        Intent intent = getIntent();
        final String[] CONTACT = intent.getStringExtra("ID").split(",");

        //checking which icons to show
        iconCheck(CONTACT);

        //hidding the setUpAccount button
        Button setUpAccountButton = (Button) findViewById(R.id.setUpAccountButton);
        setUpAccountButton.setVisibility(View.GONE);

        // a button to go back to myPeople activity
        Button backToPeople = (Button)findViewById(R.id.backToMyPeople);
        backToPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myPeople.class);
                startActivity(intent);
            }
        });

        //setting the contact image to null contact for now
        ImageView contactImage = (ImageView)findViewById(R.id.contactPicture);
        contactImage.setImageResource(R.drawable.nullcontact);

        //seting the name of the contact
        TextView nameOfContact = (TextView)findViewById(R.id.contactNameMyProfile);
        nameOfContact.setText(CONTACT[1]);

        //button listeners for image icons , to show info below in a text view
        final TextView showInfoTextView = (TextView)findViewById(R.id.showInformationTextView);
        //image buttons to display corresponding information in the text view on the bottom
        ImageButton phoneImage = (ImageButton)findViewById(R.id.imageButtonPHONENUMBER);
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(CONTACT[2]);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + CONTACT[2]));
                startActivity(intent);
            }
        });
        ImageButton snapchatImage =(ImageButton)findViewById(R.id.imageButtonSNAPCHAT);
        snapchatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(CONTACT[3]);
            }
        });
        ImageButton instagramImage = (ImageButton)findViewById(R.id.imageButtonINSTAGRAM);
        instagramImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(CONTACT[4]);
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                intent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                intent.setData(Uri.parse("http://instagram.com/_u/" + CONTACT[4]));
                startActivity(intent);
            }
        });
        ImageButton facebookImage = (ImageButton)findViewById(R.id.imageButtonFACEBOOK);
        facebookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(CONTACT[5]);
                Intent intent = new Intent("android.intent.category.LAUNCHER");
                intent.setClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
                startActivity(intent);
            }
        });
        ImageButton emailImage = (ImageButton)findViewById(R.id.imageButtonEMAIL);
        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoTextView.setText(CONTACT[6]);
                Intent email = new Intent(Intent.ACTION_VIEW);
                String emailString = CONTACT[6].substring(0, CONTACT[6].length() -1);
                Uri emailData = Uri.parse("mailto:"+emailString);
                email.setData(emailData);
                startActivity(email);
            }
        });
    }//CLOSING onCreate()



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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
