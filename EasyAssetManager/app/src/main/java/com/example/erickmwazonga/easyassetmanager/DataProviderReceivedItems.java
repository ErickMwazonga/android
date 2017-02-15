package com.example.erickmwazonga.easyassetmanager;

/**
 * Created by Erick Mwazonga on 11/15/2016.
 */

public class DataProviderReceivedItems {
    private String name, serial, description, date_received;

    public DataProviderReceivedItems(String name, String serial, String description, String date_received) {
        this.name = name;
        this.serial = serial;
        this.description = description;
        this.date_received = date_received;
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

    public String getDate_received() {
        return date_received;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }
}

