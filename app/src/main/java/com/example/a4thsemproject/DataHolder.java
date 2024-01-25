package com.example.a4thsemproject;

import android.util.Log;

public class DataHolder {
    private static final DataHolder instance = new DataHolder();
    private String name;
    private String gender;
    private String age;
    private DataHolder() {
        // Private constructor to prevent instantiation
    }
    public static DataHolder getInstance() {
        return instance;
    }
    public void setData(String name, String gender, String age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        Log.i("DataHolder", "Name: " + name + ", Gender: " + gender + ", Age: " + age);
    }
    public String getName() {
        Log.i("man",name);
        return name;
    }
    public String getGender(){
        Log.i("torrent",gender);
        return gender;
    }
    public String getAge()
    {
        return age;
    }
}
