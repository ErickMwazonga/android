package com.example.erickmwazonga.databasetestone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Erick Mwazonga on 10/20/2016.
 */
public class UserDBHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "USERINFODB";
    private static  final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UserContact.NewUserInfo.TABLE_NAME+"("+ UserContact.NewUserInfo.USER_NAME+ " TEXT," +
            UserContact.NewUserInfo.USER_EMAIL+" TEXT,"+ UserContact.NewUserInfo.USER_PASSWORD+" TEXT);";
    private Context context;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Log.e("Database Operations","Database Created");
        //Toast.makeText(context, "Database created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("Database Operations","Table Created");
        //Toast.makeText(context, "Table created",Toast.LENGTH_SHORT).show();
    }
    public void addContacts(String name,String email,String password, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContact.NewUserInfo.USER_EMAIL,email);
        contentValues.put(UserContact.NewUserInfo.USER_PASSWORD,password);
        db.insert(UserContact.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("Database Operations","A row inserted");
        //Toast.makeText(context, "A row is inserted..",Toast.LENGTH_SHORT).show();
    }

    public Cursor getInfo(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {UserContact.NewUserInfo.USER_NAME,UserContact.NewUserInfo.USER_EMAIL, UserContact.NewUserInfo.USER_PASSWORD};

        cursor = db.query(UserContact.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getContacts(String name, SQLiteDatabase sqLiteDatabase){
        String [] projections = {UserContact.NewUserInfo.USER_EMAIL, UserContact.NewUserInfo.USER_PASSWORD};
        String selection = UserContact.NewUserInfo.USER_NAME +" LIKE ?";
        String [] selection_args ={name};
        Cursor cursor = sqLiteDatabase.query(UserContact.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
