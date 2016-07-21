package com.example.dima.slideapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class MainMenu extends Activity {

    //5/11/2015 for scanner to be only one window away
    private static final int ACTIVITY_RESULT_QR_DRDROID = 0;

    Button generate;
    EditText edit;
    File file ;
    int didGenerate =0;
    DataBase DATABASE;
    String MAIN_USER ="";
    String QRCode;
    //variables from scanCodes
    String USER_NAME = "null";
    String PASSWORD = "null";
    String USER_PHONENUMBER = "null";
    String USER_SNAPCHAT ="null";
    String USER_INSTAGRAM ="null";
    String USER_FACEBOOK ="null";
    String USER_EMAIL ="null";
    boolean scanning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //opening DATABASE
        openDATABASE();
        Cursor cursor = DATABASE.getAllRows();

        int i =0;
        if (cursor.moveToFirst()) {
            String contactName = cursor.getString(DATABASE.COL_NAME);
            String contactPassword = cursor.getString(DATABASE.COL_PASSWORD);
            String contactPhonenumber = cursor.getString(DATABASE.COL_PHONENUMBER);
            String contactSnapchat = cursor.getString(DATABASE.COL_SNAPCHAT);
            String contactInstagram = cursor.getString(DATABASE.COL_INSTAGRAM);
            String contactFacebook = cursor.getString(DATABASE.COL_FACEBOOK);
            String contactEmail = cursor.getString(DATABASE.COL_EMAIL);
            MAIN_USER = contactName + "," + contactPhonenumber + "," + contactSnapchat+
                    ","+ contactInstagram + "," + contactFacebook + "," + contactEmail;
        }//closing if statement

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //button to show QR codes when clicked
            Button showQR = (Button) findViewById(R.id.showQRButton);
            showQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), showCodes.class);
                    startActivity(intent);
                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            Button searchButton = (Button) findViewById(R.id.searchButton);
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scanning = true;
                    Intent i = new Intent("la.droid.qr.scan");
                    try {
                        startActivityForResult(i, ACTIVITY_RESULT_QR_DRDROID);
                    }
                    catch (ActivityNotFoundException activity) {
                        MainMenu.qrDroidRequired(MainMenu.this);
                    }
                }
            });//closing QR button


            //button to go to myProfile
            Button myProfileButton = (Button) findViewById(R.id.myProfile);
            myProfileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), myProfile.class);
                    startActivity(intent);
                }
            });

            //button to go to myPeople
            Button myPeopleButton = (Button) findViewById(R.id.peopleButton);
            myPeopleButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), myPeople.class);
                    startActivity(intent);
                }
            });
    }//closing on create



    public void setUpEntries(String QRCODE){
        String[] entriesInArray = QRCODE.split(",");
        USER_NAME = entriesInArray[0];
        USER_SNAPCHAT = entriesInArray[1];
        USER_PHONENUMBER = entriesInArray[2];
        USER_INSTAGRAM = entriesInArray[3];
        USER_FACEBOOK = entriesInArray[4];
        USER_EMAIL = entriesInArray[5];

        for(int i=0; i<=5; i++){
            System.out.println(entriesInArray[i]);
        }
        //adding a row to the database
        long id = DATABASE.insertRow(USER_NAME, PASSWORD, USER_PHONENUMBER, USER_SNAPCHAT, USER_INSTAGRAM, USER_FACEBOOK, USER_EMAIL);
        Intent intent = new Intent(getApplicationContext(), Contact.class);
        intent.putExtra("ID", ","+QRCODE);
        startActivity(intent);
    }//clossgin setUpEntries()



    protected static void qrDroidRequired(final MainMenu activity) {
        // TODO Auto-generated method stub

        AlertDialog.Builder AlertBox = new AlertDialog.Builder(activity);

        AlertBox.setMessage("QRDroid Missing");

        AlertBox.setPositiveButton("Direct Download", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://droid.la/apk/qr/")));
            }
        });

        AlertBox.setNeutralButton("From Market", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://market.android.com/details?id=la.droid.qr")));
            }
        });

        AlertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                dialog.cancel();
            }
        });

        AlertBox.create().show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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



    @Override
    protected void onDestroy(){
        super.onDestroy();;
        closeDATABASE();

        if(didGenerate == 1) {
            if (file.exists()) {
                boolean delete = file.delete();
            }
        }
    }

    private void closeDATABASE() {
        DATABASE.close();
    }//closing closeDATABASE()

    private void openDATABASE() {
        DATABASE = new DataBase(this);
        DATABASE.open();
    }//closing openDATABASE()


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null ) {
            String qrCode = data.getExtras().getString("la.droid.qr.result");

            if(qrCode == null || qrCode.trim().length() == 0) {

                Toast.makeText(getBaseContext(), "QR Code Image " +
                        "is not Saved", Toast.LENGTH_LONG).show();
                return;
            }
            QRCode = qrCode;
            file = new File(qrCode);
            didGenerate =1;
        }

        //qr codes for scanning
        if( ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null  && scanning ==true ) {
            String result = data.getExtras().getString("la.droid.qr.result");
            QRCode = result;
            didGenerate =1;
            //setting the fields for the database entries
            setUpEntries(QRCode);
        }
    }


}
