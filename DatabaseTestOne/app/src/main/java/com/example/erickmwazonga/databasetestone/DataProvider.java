package com.example.erickmwazonga.databasetestone;

/**
 * Created by Erick Mwazonga on 10/20/2016.
 */
public class DataProvider {

    private String name;
    private String email;
    private String password;

    public  DataProvider(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
