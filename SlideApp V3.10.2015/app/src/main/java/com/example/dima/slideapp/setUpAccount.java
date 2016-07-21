package com.example.dima.slideapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class setUpAccount extends ActionBarActivity {

    //creating the dataBase
    DataBase DATABASE;
    String MainUserName = "null user name";
    String[] CONTACTSDB = new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //opening the data base
        openDATABASE();

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //getting data from the database and modifying it
        String[] MAIN_USER;
        Cursor cursor = DATABASE.getAllRows();
        int i =0;
        if (cursor.moveToFirst()) {
            do {
                String contactName = cursor.getString(DATABASE.COL_NAME);
                String contactPassword = cursor.getString(DATABASE.COL_PASSWORD);
                String contactPhonenumber = cursor.getString(DATABASE.COL_PHONENUMBER);
                String contactSnapchat = cursor.getString(DATABASE.COL_SNAPCHAT);
                long contactID = cursor.getLong(DATABASE.COL_ROWID);

                String contactInstagram = cursor.getString(DATABASE.COL_INSTAGRAM);
                String contactFacebook = cursor.getString(DATABASE.COL_FACEBOOK);
                String contactEmail = cursor.getString(DATABASE.COL_EMAIL);

                CONTACTSDB[i] = contactName + "," + contactPassword + "," + contactPhonenumber + "," + contactSnapchat + "," + contactID +
                        ","+ contactInstagram + "," + contactFacebook + "," + contactEmail;
                i++;
            } while (cursor.moveToNext());
        }//closing if statement

        MAIN_USER = CONTACTSDB[0].split(",");

        //getting the main user ID
        final long contactID = Long.parseLong(MAIN_USER[4]);
        //getting the main users password
        final String MAIN_USER_PASSWORD = MAIN_USER[1];

        EditText nameOfContact = (EditText) findViewById(R.id.editTextNAME);
        nameOfContact.setText(MAIN_USER[0]);

        EditText phoneNumberOfContact = (EditText) findViewById(R.id.editTextPHONENUMBER);
        if(MAIN_USER[3].equals("null")){
            phoneNumberOfContact.setText("");
        }else{
            phoneNumberOfContact.setText(MAIN_USER[3]);
        }


        EditText snapChatNameOfContact = (EditText) findViewById(R.id.editTextSNAPCHAT);
        if(MAIN_USER[2].equals("null")){
            snapChatNameOfContact.setText("");
        }else {
            snapChatNameOfContact.setText(MAIN_USER[2]);
        }

        final EditText instagramOfContact = (EditText)findViewById(R.id.editTextINSTAGRAM);
        if(MAIN_USER[5].equals("null")){
            instagramOfContact.setText("");
        }else {
            instagramOfContact.setText(MAIN_USER[5]);
        }

        EditText facebookOfContact = (EditText)findViewById(R.id.editTextFACEBOOK);
        if(MAIN_USER[6].equals("null")){
            facebookOfContact.setText("");
        }else {
            facebookOfContact.setText(MAIN_USER[6]);
        }

        EditText emailOfContact = (EditText)findViewById(R.id.editTextEMAIL);
        if(MAIN_USER[7].equals("null")){
            emailOfContact.setText("");
        }else {
            emailOfContact.setText(MAIN_USER[7]);
        }

        //////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////

        //button for setting changes to "myProfile"
        Button setChangesButton = (Button)findViewById(R.id.signUpButton);
        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameOfContact = (EditText) findViewById(R.id.editTextNAME);
                String contactName = nameOfContact.getText().toString();
                if(contactName.equals("")){
                    contactName = "null";
                }

                EditText phoneNumberOfContact = (EditText) findViewById(R.id.editTextPHONENUMBER);
                String phoneNumber = phoneNumberOfContact.getText().toString();
                if(phoneNumber.equals("")){
                    phoneNumber = "null";
                }

                EditText snapChatNameOfContact = (EditText) findViewById(R.id.editTextSNAPCHAT);
                String snapChat = snapChatNameOfContact.getText().toString();
                if(snapChat.equals("")){
                    snapChat = "null";
                }

                EditText instaOfContact = (EditText)findViewById(R.id.editTextINSTAGRAM);
                String instagram = instaOfContact.getText().toString();
                if(instagram.equals("")){
                    instagram = "null";
                }

                EditText facebookOfContact = (EditText)findViewById(R.id.editTextFACEBOOK);
                String facebook = facebookOfContact.getText().toString();
                if(facebook.equals("")){
                    facebook = "null";
                }

                EditText emailOfContact = (EditText)findViewById(R.id.editTextEMAIL);
                String email = emailOfContact.getText().toString();
                if(email.equals("")){
                    email = "null";
                }

                boolean A =DATABASE.updateRow(contactID , contactName, MAIN_USER_PASSWORD ,phoneNumber, snapChat, instagram, facebook,
                        email);

                Intent intent = new Intent(getApplicationContext(), myProfile.class);
                startActivity(intent);


            }
        });

    }//closing onCreate()

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
        getMenuInflater().inflate(R.menu.menu_set_up_account, menu);
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
