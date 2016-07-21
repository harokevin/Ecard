package com.example.dima.slideapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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


public class showCodes extends Activity {

    private static final int ACTIVITY_RESULT_QR_DRDROID = 0;
    Button generate;
    EditText edit;
    File file ;
    int didGenerate =0;

    DataBase DATABASE;
    String MAIN_USER ="";
    String QRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_qr_codes);

        //opening the database
        openDATABASE();
        Cursor cursor = DATABASE.getAllRows();

        int i =0;
        if (cursor.moveToFirst()) {
            //do {
            String contactName = cursor.getString(DATABASE.COL_NAME);
            String contactPassword = cursor.getString(DATABASE.COL_PASSWORD);
            String contactPhonenumber = cursor.getString(DATABASE.COL_PHONENUMBER);
            String contactSnapchat = cursor.getString(DATABASE.COL_SNAPCHAT);
            String contactInstagram = cursor.getString(DATABASE.COL_INSTAGRAM);
            String contactFacebook = cursor.getString(DATABASE.COL_FACEBOOK);
            String contactEmail = cursor.getString(DATABASE.COL_EMAIL);
            MAIN_USER = contactName + "," + contactPhonenumber + "," + contactSnapchat+
                    ","+ contactInstagram + "," + contactFacebook + "," + contactEmail;

            //} while (cursor.moveToNext() && i == 0);
            //i++;
        }//closing if statement


        String code ;
        //adding a line from database
        code = MAIN_USER;
        System.out.println(code);

        Intent encode = new Intent("la.droid.qr.encode");
        encode.putExtra("la.droid.qr.code", code);
        encode.putExtra("la.droid.qr.image", true);
        encode.putExtra("la.droid.qr.size", 0);
        try {
            startActivityForResult(encode, ACTIVITY_RESULT_QR_DRDROID);
        }
        catch (ActivityNotFoundException activity) {
            qrDroidRequired(showCodes.this);
        }


        //button to go back to the main menu
        Button backHome = (Button)findViewById(R.id.button1);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });//clsoing on click listener for the "go back home " button

    }//clossing onCreate()



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null ) {

            ImageView imgResult = ( ImageView ) findViewById(R.id.imageView);

            String qrCode = data.getExtras().getString("la.droid.qr.result");

            if(qrCode == null || qrCode.trim().length() == 0) {

                Toast.makeText(getBaseContext(), "QR Code Image " +
                        "is not Saved", Toast.LENGTH_LONG).show();
                return;
            }
            imgResult.setImageURI( Uri.parse(qrCode) );
            QRCode = qrCode;
            file = new File(qrCode);
            didGenerate =1;
        }
    }//closing onActivityResult()


    protected static void qrDroidRequired(final showCodes activity) {
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
    }//closing WR method()


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Nothing
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
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
