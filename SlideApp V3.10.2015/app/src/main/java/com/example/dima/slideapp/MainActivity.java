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
import android.widget.TextView;

import java.sql.BatchUpdateException;


public class MainActivity extends ActionBarActivity {
    DataBase DATABASE;
    String[] CONTACTSDB = new String[100];
    String[] MAIN_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        openDATABASE();
        /////////////////////////////////////////login page

        //login button activity
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = (EditText) findViewById(R.id.userNameTextLogin);
                String usernameInput = userName.getText().toString();

                EditText passwordLogin = (EditText) findViewById( R.id.passwordEditTextLogin);
                String passwordInput = passwordLogin.getText().toString();

                            //getting information from the database to check for user name
                            Cursor cursor = DATABASE.getAllRows();
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
                                    CONTACTSDB[i] = contactName + "," + contactPassword + "," + contactPhonenumber + "," + contactSnapchat+
                                    ","+ contactInstagram + "," + contactFacebook + "," + contactEmail;
                                    i++;
                                } while (cursor.moveToNext());
                            }//closing if statement

                            MAIN_USER = CONTACTSDB[0].split(",");
                //checking if the username and password maatches
                if(MAIN_USER[0].equals(usernameInput) && MAIN_USER[1].equals(passwordInput)){
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                }//closing if statement

            }
        });

///////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //in the register activity
        Button registerButtonActivity = (Button) findViewById(R.id.registerButton);
        registerButtonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_register);

                Button registerButton = (Button) findViewById(R.id.registerAccount);
                registerButton.setOnClickListener(new View.OnClickListener() {
                    TextView registerResponse = (TextView) findViewById(R.id.registerActivityResponse);
                    @Override
                    public void onClick(View v) {
                        //clearing the database

                            DATABASE.deleteAll();


                        EditText userNameEditText = (EditText) findViewById(R.id.userNameTextRegister);
                        String USERNAME = userNameEditText.getText().toString();

                        EditText password1 = (EditText) findViewById(R.id.passwordEditTextRegister);
                        String PASSWORDONE = password1.getText().toString();

                        EditText password2 = (EditText) findViewById(R.id.passwordEditTextRegister2);
                        String PASSWORDTWO = password2.getText().toString();

                        //checking if the password is the same in both text entries
                        if(PASSWORDONE.equals(PASSWORDTWO)) {
                            registerResponse.setText("Good Password! :)");
                            //starting the SETUPACCOUNT activity
                            Intent intent = new Intent(getApplicationContext(), iconsForRegister.class);
                            //passing the userName and password to SETUPACCOUNT activity
                            intent.putExtra("String",USERNAME);
                            intent.putExtra("Password", PASSWORDONE);
                            startActivity(intent);

                        }
                        else {
                            registerResponse.setText("Password does not match! TRY AGAIN");
                        }//closing if-else statement

                    }
                });

            }
        });

    }//closing onCreate()


    private void openDATABASE() {
        DATABASE = new DataBase(this);
        DATABASE.open();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();;
        closeDATABASE();
    }

    private void closeDATABASE() {
        DATABASE.close();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
