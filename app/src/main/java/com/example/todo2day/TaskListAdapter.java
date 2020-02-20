package com.example.todo2day;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todo2day.model.Task;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;
    private List<Task> mAllTasks;
    public TaskListAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mAllTasks = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, null);
        CheckBox isDoneCheckBox = view.findViewById(R.id.isDoneCheckBox);

        Task selectedTask = mAllTasks.get(position);

        isDoneCheckBox.setTag(selectedTask);

        isDoneCheckBox.setChecked(selectedTask.ismIsDone());
        isDoneCheckBox.setText(selectedTask.getmDescription());



        return view;
    }
}
