package com.example.erickmwazonga.easyassetmanager;

/**
 * Created by Erick Mwazonga on 11/15/2016.
 */

public class DataProviderIssuedItems {
    private String name, serial, user, date_issued;

    public DataProviderIssuedItems(String name, String serial, String user, String date_issued) {
        this.name = name;
        this.serial = serial;
        this.user = user;
        this.date_issued = date_issued;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_issued() {
        return date_issued;
    }

    public void setDate_issued(String date_issued) {
        this.date_issued = date_issued;
    }
}
