package com.example.dima.slideapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class Register extends ActionBarActivity {

    //creating the dataBase
    DataBase DATABASE;
    String[] CONTACTSDB = new String[100];
    //creating fields for each icon
    String USER_NAME;
    String USER_PHONENUMBER = "null";
    String USER_SNAPCHAT ="null";
    String USER_INSTAGRAM ="null";
    String USER_FACEBOOK ="null";
    String USER_EMAIL ="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //opening the data base
        openDATABASE();

        //getting the uUSERNAME and PASSWORD from the register activity
        Intent extrasIntent = getIntent();
        final String USERNAME = extrasIntent.getStringExtra("USERNAME");
        USER_NAME = USERNAME;
        final String PASSWORD = extrasIntent.getStringExtra("PASSWORD");
        final int[] SELECTED_ICONS = extrasIntent.getIntArrayExtra("SELECTED_ICONS");
        iconCheck2(SELECTED_ICONS);
        //setting the userName from register activity
        EditText setNameToUSERNAME = (EditText) findViewById(R.id.editTextNAME);
                setNameToUSERNAME.setText(USERNAME);
        ///////////////////////////////////

        //Button for SIGN UP, it will added the first row of new user into the database
        Button signUpBUtton = (Button) findViewById(R.id.signUpButton);
        signUpBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding the frist row for the new DATABASE
                iconCheck(SELECTED_ICONS);//checking icons
                //EditText phoneNumberTextEdit = (EditText) findViewById(R.id.editTextPHONENUMBER);
                //String USERPHONENUMBER = phoneNumberTextEdit.getText().toString();
                long id = DATABASE.insertRow(USER_NAME, PASSWORD, USER_PHONENUMBER, USER_SNAPCHAT, USER_INSTAGRAM, USER_FACEBOOK, USER_EMAIL);
                //long id = DATABASE.insertRow(USER_NAME, PASSWORD, "USER_PHONENUMBER", "USER_SNAPCHAT", "USER_INSTAGRAM", "USER_FACEBOOK", "USER_EMAIL");

                //going to the main menu
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("ARRAY", SELECTED_ICONS);
                startActivity(intent);
            }
        });
    }//closing onCreate()


    //a method to check which icons are disabled
    public void iconCheck(int[] SELECTED_ICONS){
        //checking what icons have been checked during ICONS_FOR_REGISTER activity
        ////////first if statement will check if PHONENUMBER icon has been clicked or not

        if(SELECTED_ICONS[0]==0){
            //this means the PHONEUMBER icon has NOT been clicked and we need to disable it
            //hidding a textEdit PHONENUMBER AND phonumber IMAGE ICON
            //EditText phoneNumberTextEdit = (EditText) findViewById(R.id.editTextPHONENUMBER);
            //phoneNumberTextEdit.setVisibility(View.GONE);
            //ImageView phoneNumberImageView = (ImageView) findViewById(R.id.imageViewPHONENUMBER);
            //phoneNumberImageView.setVisibility(View.GONE);
            //setting the USER_PHONENUMBER to null
           USER_PHONENUMBER = "null";
        }else{
            EditText phoneNumberTextEdit = (EditText) findViewById(R.id.editTextPHONENUMBER);
            USER_PHONENUMBER = phoneNumberTextEdit.getText().toString();
        }//closing the first if-else statement

        EditText snapchatTextEdit = (EditText) findViewById(R.id.editTextSNAPCHAT);
        if(SELECTED_ICONS[1]==0){
            //this means SNAPCHAT icon has NOT been clicked and we need to diasble it
            //hidding a TextEdit with snapchat and SNAPCHAT IMAGE ICON
            //snapchatTextEdit.setVisibility(View.GONE);
            //ImageView snapchatImageView = (ImageView) findViewById(R.id.imageViewSNAPCHAT);
            //snapchatImageView.setVisibility(View.GONE);
            USER_SNAPCHAT = "null";
        }else{
            USER_SNAPCHAT = snapchatTextEdit.getText().toString();
        }//closing the second if statement

        EditText instagramTextEdit = (EditText) findViewById(R.id.editTextINSTAGRAM);
        if(SELECTED_ICONS[2]==0){
            //hiding the icon for INSTAGRAM
               // instagramTextEdit.setVisibility(View.GONE);
            //ImageView imageViewInstagram = (ImageView) findViewById(R.id.imageViewINSTAGRAM);
              //  imageViewInstagram.setVisibility(View.GONE);
            USER_INSTAGRAM = "null";
        }else{
            USER_INSTAGRAM = instagramTextEdit.getText().toString();
        }//closing the 3rd if statement

        EditText facebookEditText =(EditText) findViewById(R.id.editTextFACEBOOK);
        if(SELECTED_ICONS[3]==0){
            //diasbiling the icon and image view for FACEBOOK
            //    facebookEditText.setVisibility(View.GONE);
            //ImageView imageViewFacebook = (ImageView) findViewById(R.id.imageViewFACEBOOK);
              //  imageViewFacebook.setVisibility(View.GONE);
            USER_FACEBOOK = "null";
        }else{
            USER_FACEBOOK = facebookEditText.getText().toString();
        }//closing 4th if statement

        EditText emailEditText = (EditText) findViewById(R.id.editTextEMAIL);
        if(SELECTED_ICONS[4]==0){
                //emailEditText.setVisibility(View.GONE);
            //ImageView imageViewEmail = (ImageView) findViewById(R.id.imageViewEMAIL);
              //  imageViewEmail.setVisibility(View.GONE);
            USER_EMAIL = "null";
        }else{
            USER_EMAIL = emailEditText.getText().toString();
        }//closing the 5th if statement
    }//closing iconCheck()

    //a method to get information from textEdits
    public void iconCheck2(int[] SELECTED_ICONS){
        //checking what icons have been checked during ICONS_FOR_REGISTER activity
        ////////first if statement will check if PHONENUMBER icon has been clicked or not

        if(SELECTED_ICONS[0]==0){
            //this means the PHONEUMBER icon has NOT been clicked and we need to disable it
            //hidding a textEdit PHONENUMBER AND phonumber IMAGE ICON
            EditText phoneNumberTextEdit = (EditText) findViewById(R.id.editTextPHONENUMBER);
            phoneNumberTextEdit.setVisibility(View.GONE);
            ImageView phoneNumberImageView = (ImageView) findViewById(R.id.imageViewPHONENUMBER);
            phoneNumberImageView.setVisibility(View.GONE);
            //setting the USER_PHONENUMBER to null
            //\USER_PHONENUMBER = null;
        }else{
            //EditText phoneNumberTextEdit = (EditText) findViewById(R.id.editTextPHONENUMBER);
            //USER_PHONENUMBER = phoneNumberTextEdit.getText().toString();
        }//closing the first if-else statement

        EditText snapchatTextEdit = (EditText) findViewById(R.id.editTextSNAPCHAT);
        if(SELECTED_ICONS[1]==0){
            //this means SNAPCHAT icon has NOT been clicked and we need to diasble it
            //hidding a TextEdit with snapchat and SNAPCHAT IMAGE ICON
            snapchatTextEdit.setVisibility(View.GONE);
            ImageView snapchatImageView = (ImageView) findViewById(R.id.imageViewSNAPCHAT);
            snapchatImageView.setVisibility(View.GONE);
           // USER_SNAPCHAT = null;
        }else{
           // USER_SNAPCHAT = snapchatTextEdit.getText().toString();
        }//closing the second if statement

        EditText instagramTextEdit = (EditText) findViewById(R.id.editTextINSTAGRAM);
        if(SELECTED_ICONS[2]==0){
            //hiding the icon for INSTAGRAM
            instagramTextEdit.setVisibility(View.GONE);
            ImageView imageViewInstagram = (ImageView) findViewById(R.id.imageViewINSTAGRAM);
            imageViewInstagram.setVisibility(View.GONE);
           // USER_INSTAGRAM = null;
        }else{
            //USER_INSTAGRAM = instagramTextEdit.getText().toString();
        }//closing the 3rd if statement

        EditText facebookEditText =(EditText) findViewById(R.id.editTextFACEBOOK);
        if(SELECTED_ICONS[3]==0){
            //diasbiling the icon and image view for FACEBOOK
            facebookEditText.setVisibility(View.GONE);
            ImageView imageViewFacebook = (ImageView) findViewById(R.id.imageViewFACEBOOK);
            imageViewFacebook.setVisibility(View.GONE);
            //USER_FACEBOOK = null;
        }else{
            //USER_FACEBOOK = facebookEditText.getText().toString();
        }//closing 4th if statement

        EditText emailEditText = (EditText) findViewById(R.id.editTextEMAIL);
        if(SELECTED_ICONS[4]==0){
            emailEditText.setVisibility(View.GONE);
            ImageView imageViewEmail = (ImageView) findViewById(R.id.imageViewEMAIL);
            imageViewEmail.setVisibility(View.GONE);
            //USER_EMAIL = null;
        }else{
            //USER_EMAIL = emailEditText.getText().toString();
        }//closing the 5th if statement
    }//closing iconCheck()


    @Override
    protected void onDestroy(){
        super.onDestroy();
        closeDATABASE();
    }//closing onDestroy()

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
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
