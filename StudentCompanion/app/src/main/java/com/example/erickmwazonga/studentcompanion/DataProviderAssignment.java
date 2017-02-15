package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class DataProviderAssignment {
    String name;
    String subject;
    String description;
    String status;
    String date;
    String time;

//    public DataProviderAssignment(String name,String subject,String description,String status,String date,String time){
    public DataProviderAssignment(String name,String status,String date,String time){
        this.name= name;
//        this.subject= subject;
//        this.description= description;
        this.status= status;
        this.date= date;
        this.time= time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
