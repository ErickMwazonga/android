package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class DataProviderExam {
    String name;
    String description;
    String venue;
    String date;
    String time;

//    public DataProviderExam(String name, String description,String venue,String date,String time){
    public DataProviderExam(String name,String venue,String date,String time){
        this.name=name;
//        this.description=description;
        this.venue=venue;
        this.date=date;
        this.time=time;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
