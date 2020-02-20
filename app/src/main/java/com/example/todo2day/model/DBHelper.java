package com.example.todo2day.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //TASK 1: make constants for all database values
    //database name, version, table name, field names, primary key

    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String TABLE_NAME = "Tasks";
    public static final int VERSION = 1;


    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_IS_DONE + " INTEGER" + ")";

        Log.i(DATABASE_NAME,createSQL);
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1) drop the old table and recreate new
        if(newVersion > oldVersion)
        {
            String dropSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

            Log.i(DATABASE_NAME, dropSQL);
            db.execSQL(dropSQL);

            onCreate(db);
        }
    }

    /**
     * Adds a new task to the database
     * @param task The new task to be added
     * @return newly assigned id
     */
    public long addTask(Task task)
    {
        long id;
        //decide whether reading or writing to/from the
        //database
        //for adding we're writing
        SQLiteDatabase db = getWritableDatabase();

        //when we add to the database, use a date structure
        //called ContentValues (key, value) pairs

        ContentValues values= new ContentValues();

        values.put(FIELD_DESCRIPTION, task.getmDescription());
        values.put(FIELD_IS_DONE, task.ismIsDone() ? 1 : 0);

        id = db.insert(TABLE_NAME, null, values);

        //after done, close the connection to database
        db.close();
        return id;
    }

    public List<Task> getAllTasks()
    {
        List<Task> allTasks = new ArrayList<>();
        //get the tasks from the database
        SQLiteDatabase db = getReadableDatabase();

        // query the database to retrieve all records
        // store them in a structure known as a cursor
        Cursor cursor = db.query(TABLE_NAME,
                new String[] {KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_IS_DONE},
                null,
                null,
                null,
                null,
                null);
        //loop through the cursor results, one at a time
        //instantiate a new task object
        //add the new task to the list

        if (cursor.moveToFirst())
        {
            do{
                long id = cursor.getLong(0);
                String description = cursor.getString(1);
                boolean isDone = cursor.getInt(2) == 1;

                Task newTask = new Task(id, description, isDone);
                allTasks.add(newTask);

            }while(cursor.moveToNext());
        }
        //close the cursor
        cursor.close();
        //close the connection
        db.close();

        return allTasks;
    }


    public void clearAllTasks()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);


        db.close();
    }
}
