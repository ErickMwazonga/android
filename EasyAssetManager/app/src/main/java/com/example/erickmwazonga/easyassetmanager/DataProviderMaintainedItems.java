package com.example.erickmwazonga.easyassetmanager;

/**
 * Created by Erick Mwazonga on 11/15/2016.
 */

public class DataProviderMaintainedItems {
    private String name, serial, reason, maintainer;

    public DataProviderMaintainedItems(String name, String serial, String reason, String maintainer) {
        this.name = name;
        this.serial = serial;
        this.reason = reason;
        this.maintainer = maintainer;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }
}

