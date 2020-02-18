package com.example.todo2day.model;

public class Task {


    private int mId;
    private String mDescription;
    private boolean mIsDone;




    public Task(int mId, String mDescription, boolean mIsDone) {
        this.mId = mId;
        this.mDescription = mDescription;
        this.mIsDone = mIsDone;
    }

    public Task(String description, boolean isDone)
    {
        this(-1,description,isDone);
    }

    public Task(String description)
    {
        this(-1, description,false);
    }

    @Override
    public String toString() {
        return "Task{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mIsDone=" + mIsDone +
                '}';
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean ismIsDone() {
        return mIsDone;
    }

    public void setmIsDone(boolean mIsDone) {
        this.mIsDone = mIsDone;
    }
}
