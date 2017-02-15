package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class DataProviderHoliday {
    String name;
    String start;
    String end;

    public DataProviderHoliday(String name,String start,String end){
        this.name=name;
        this.start=start;
        this.end=end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
