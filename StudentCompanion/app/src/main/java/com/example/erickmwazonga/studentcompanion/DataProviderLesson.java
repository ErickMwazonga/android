package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class DataProviderLesson {
    String day ;
    String name;
    String code;
    String teacher ;
    String venue ;
    String time ;

//    public DataProviderLesson(String name,String day,String code, String teacher,String venue,String time){
    public DataProviderLesson(String name,String venue,String time){
        this.name=name;
//        this.day=day;
//        this.code=code;
//        this.teacher=teacher;
        this.venue=venue;
        this.time=time;
    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(String teacher) {
//        this.teacher = teacher;
//    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
