package com.jasonbutwell.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //try {

            // Open or create database, depending on whether it exists or not.

            // Create if it doesn't and open if it does.

            SQLiteDatabase myDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            // query to drop the Users table for testing purposes only
//            myDB.execSQL("DROP TABLE IF EXISTS Users;");

            // query to create the table users if it doesnt exist
            // Using two fields, one for name, and one for age

//            myDB.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3));");

            // query to insert some example data into the database

//            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Bob',45);");
//            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Jason', 42);");

            // Get data out of database with a cursor object, passing in the query to return the data we want
            // the null is for a method to handle the cancelling of the query if used for a bigger query.

            Cursor cursor = myDB.rawQuery("SELECT * FROM users;", null);

            // obtain indexes for the two fields

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            // moves the cursor to the first result
            cursor.moveToFirst();

            // A way for use to find out how many records the query returned.
            int count = cursor.getCount();

            // Send the number of records found to the log
            Log.i( "sql" , "count: " + Integer.toString(count));

            // loop for all the records returned

            while (count > 0) {

                // Obtain strings of the result set for name and age using the indexes

                Log.i("sql", "name: " + cursor.getString(nameIndex));
                Log.i("sql", "age: " + cursor.getString(ageIndex));

                // Move the cursor to the next result
                cursor.moveToNext();

                // Decrement our loop counter which is based on the number of results returned
                count--;
            }
//        }
//        catch (Exception e ) {
//            e.printStackTrace();
//        }
    }
}
