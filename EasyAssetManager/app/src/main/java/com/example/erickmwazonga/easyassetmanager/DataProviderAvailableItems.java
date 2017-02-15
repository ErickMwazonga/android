package com.example.erickmwazonga.easyassetmanager;

/**
 * Created by Erick Mwazonga on 11/14/2016.
 */

public class DataProviderAvailableItems {
    private String name, serial, description, quantity;

    public DataProviderAvailableItems(String name, String serial, String description, String quantity) {
        this.name = name;
        this.serial = serial;
        this.description = description;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
