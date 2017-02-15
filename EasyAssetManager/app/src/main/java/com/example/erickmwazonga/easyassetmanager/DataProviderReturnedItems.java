package com.example.erickmwazonga.easyassetmanager;

/**
 * Created by Erick Mwazonga on 11/15/2016.
 */

public class DataProviderReturnedItems {
    private String name, serial, description, date_returned;

    public DataProviderReturnedItems(String name, String serial, String description, String date_returned) {
        this.name = name;
        this.serial = serial;
        this.description = description;
        this.date_returned = date_returned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(String date_returned) {
        this.date_returned = date_returned;
    }
}

