package com.example.dima.slideapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class myPeople extends ActionBarActivity {

    DataBase DATABASE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_people);

        openDATABASE();
        //getting all of the contacts from the database
        Cursor cursor = DATABASE.getAllRows();
        getAllContacts(cursor);


        //a button listener to back to the main menu
        Button backToMainMenu = (Button)findViewById(R.id.backToMainMenu);
        backToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });

    }//clossing onCreate()

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
        getMenuInflater().inflate(R.menu.menu_my_people, menu);
        return true;
    }

    private void showContacts(ArrayList<String> message) {
        ArrayList<String> NameArray = new ArrayList<>();//an array for all the names in the database
        final ArrayList<String> IDArray = new ArrayList<>();//an array for ID's
        final String[] CONTACTS = message.toArray(new String[message.size()]);//converting arrayList to array
        //a for-loop to get all of the names
        for(int i=0; i<CONTACTS.length; i++){
            String[] tempArray = CONTACTS[i].split(",");
            NameArray.add(tempArray[1]);//adding the names of contacts from the database
            IDArray.add(tempArray[0]);//adding the ID from the database
        }//closing for-loop

        final String[] namesInArray = NameArray.toArray(new String[NameArray.size()]);//converting arraylist to array
        final String[] IDInArray = IDArray.toArray(new String[IDArray.size()]);//converting the ID to array
        //creating a listview refrence to show the contact names
        final ListView listView = (ListView) findViewById(R.id.showContacts);
        //an array adapter for the listView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,namesInArray);
            listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//onClick item listener for the listView
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //checking which contact has been selected
                String selectedName = (String) listView.getItemAtPosition(position);
                for(int i=0; i<namesInArray.length; i++){

                    if(selectedName.equals(namesInArray[i])){//when the name matches it will open the profile of a person
                        Intent intent =  new Intent(getApplicationContext(), Contact.class);
                            intent.putExtra("ID", CONTACTS[i]);//
                        startActivity(intent);
                    }//closing if statement
                }//ending for-loop
            }
        });
    }//closing showCONTACTS()


    // Display an entire recordset to the screen.
    private void getAllContacts(Cursor cursor) {
        String message = "";
        ArrayList<String> CONTACTS = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                // Process the data:
                int id = cursor.getInt(DATABASE.COL_ROWID);
                String name = cursor.getString(DATABASE.COL_NAME);
                String phoneNumber = cursor.getString(DATABASE.COL_SNAPCHAT);
                String snapChat = cursor.getString(DATABASE.COL_PHONENUMBER);
                String instagram = cursor.getString(DATABASE.COL_INSTAGRAM);
                String facebook = cursor.getString(DATABASE.COL_FACEBOOK);
                String email = cursor.getString(DATABASE.COL_EMAIL);

                // Append data to the message:
                message = id
                        +"," + name
                        +"," + phoneNumber
                        +"," + snapChat
                        +"," + instagram
                        +"," + facebook
                        +"," +email
                        +"\n";

                CONTACTS.add(message);

            } while(cursor.moveToNext());
        }
        // Close the cursor to avoid a resource leak.
        cursor.close();
        showContacts(CONTACTS);
    }//closing getAllContacs()



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
