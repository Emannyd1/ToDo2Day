package com.example.todo2day;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo2day.model.DBHelper;
import com.example.todo2day.model.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //create a reference to the database we built
    private DBHelper mDB;
    private List<Task> mAllTasks;
    private EditText descriptionEditText;
    private ListView taskListView;
    private TaskListAdapter mTaskListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionEditText = findViewById(R.id.taskEditText);
        taskListView = findViewById(R.id.taskListView);

        mDB = new DBHelper(this);

        //mDB.clearAllTasks();
        /*
        mDB.addTask(new Task("Wash the dishes YOU IDIOT"));
        mDB.addTask(new Task("SLEEEEEEP"));
        mDB.addTask(new Task("Don't forget to eat"));
        mDB.addTask(new Task("Watch that movie"));
        */


        mAllTasks = mDB.getAllTasks();


        //instantiate listAdapter
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTasks);

        taskListView.setAdapter(mTaskListAdapter);

        //dummy data to test db
        /*
        mDB.addTask(new Task("Wash the dishes YOU IDIOT"));
        mDB.addTask(new Task("SLEEEEEEP"));
        mDB.addTask(new Task("Don't forget to eat"));
        mDB.addTask(new Task("Watch that movie"));
        */


    }

    public void addTask(View v)
    {
        //let's extract the description from the edit text
        String description = descriptionEditText.getText().toString();
        //id is -1, description is whatever user typed in, isDone = false
        Task newTask = new Task(description);
        long id = mDB.addTask(newTask);
        newTask.setmId(id);
        //add the new task to the list
        mAllTasks.add(newTask);
        //update listView
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void clearAllTasks(View v)
    {
        mAllTasks.clear();
        mDB.clearAllTasks();
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void changeTaskStatus(View v)
    {

        Task selectedTask = (Task) v.getTag();
        selectedTask.setmIsDone(!selectedTask.ismIsDone());
    }

    //ctrl + o
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }
}
