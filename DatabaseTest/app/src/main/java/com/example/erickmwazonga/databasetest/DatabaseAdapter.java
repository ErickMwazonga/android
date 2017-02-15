package com.example.erickmwazonga.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erick Mwazonga on 9/12/2016.
 */
public class DatabaseAdapter {

    uniqueHelper uniqueHelper;
    DatabaseAdapter(Context context){
        uniqueHelper=new uniqueHelper(context);
    }

    public Long insertData(String name,String email, String password){
        SQLiteDatabase db=uniqueHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(uniqueHelper.NAME, name);
        contentValues.put(uniqueHelper.EMAIL,email);
        contentValues.put(uniqueHelper.PASSWORD,password);

        Long id=db.insert(uniqueHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    static class uniqueHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME="STUDENTCOMPANION";
        private static final String TABLE_NAME="REGISTER";
        private static final int DATABASE_VERSION=5;
        private static final String UID="_id";
        private static final String NAME="Name";
        private static final String EMAIL="email";
        private static final String PASSWORD="Password";

//        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
//                ""+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255))";

        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+
                "VARCHAR(255), "+EMAIL+"VARCHAR(255),"+PASSWORD+" VARCHAR(255))";
        private static final String DROP_TABLE="DROP TABLE  IF EXISTS "+TABLE_NAME;
        private Context context;

        public uniqueHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            //Toast.makeText(context,"Custructor called",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            }catch (SQLException e) {
               // Toast.makeText(context, (CharSequence) e,Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (SQLException e){
               // Toast.makeText(context, (CharSequence) e,Toast.LENGTH_LONG).show();
            }
        }
    }

}
