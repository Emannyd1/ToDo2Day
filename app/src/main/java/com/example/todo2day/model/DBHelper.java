package com.example.todo2day.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //TASK 1: make constants for all database values
    //database name, version, table name, field names, primary key

    public static final String DATABSE_NAME = "ToDo2Day";
    public static final String TABLE_NAME = "Tasks";
    public static final int VERSION = 1;


    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";



    public DBHelper(@Nullable Context context) {
        super(context, DATABSE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_IS_DONE + " INTEGER" + ")";

        Log.i(DATABSE_NAME,createSQL);
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1) drop the old table and recreate new
        if(newVersion > oldVersion)
        {
            String dropSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.i(DATABSE_NAME, dropSQL);
            db.execSQL(dropSQL);

            onCreate(db);
        }
    }

    /**
     * Adds a new task to the database
     * @param task The new task to be added
     */
    public void addTask(Task task)
    {
        //decide whether reading or writing to/from the
        //database
        //for adding we're writing
        SQLiteDatabase db = getWritableDatabase();

        //when we add to the database, use a date structure
        //called ContentValues (key, value) pairs

        ContentValues values= new ContentValues();

        values.put(FIELD_DESCRIPTION, task.getmDescription());
        values.put(FIELD_IS_DONE, task.ismIsDone() ? 1 : 0);

        db.insert(TABLE_NAME, null, values);
        //after done, close the connection to database
        db.close();
    }
}
