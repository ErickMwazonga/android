package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class DataProviderExercise {
    String name;
    String description;
    String date;
    String time;

    //public DataProviderExercise(String name, String description, String date, String time) {
    public DataProviderExercise(String name, String date, String time) {
        this.name = name;
        //this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
