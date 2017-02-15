package com.example.erickmwazonga.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class uniqueDatabaseAdapter {

    uniqueHelper helper;
    uniqueDatabaseAdapter(Context context){
        helper=new uniqueHelper(context);
    }
    public Long insertData(String name,String Password){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(uniqueHelper.NAME,name);
        contentValues.put(uniqueHelper.PASSWORD,Password);

        Long id=db.insert(uniqueHelper.TABLE_NAME,null,contentValues);
        return id;
    }

        public String getAllData(){
        SQLiteDatabase db=helper.getWritableDatabase();
        //SELECT THE 3 COLUMNS ID, NAME AND PASSWORD
        String [] columns={uniqueHelper.UID,uniqueHelper.NAME,uniqueHelper.PASSWORD};
        Cursor cursor=db.query(uniqueHelper.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            int index1=cursor.getColumnIndex(uniqueHelper.UID);
            int cid=cursor.getInt(index1);
            int index2=cursor.getColumnIndex(uniqueHelper.NAME);
            String name=cursor.getString(index2);
            int index3=cursor.getColumnIndex(uniqueHelper.PASSWORD);
            String password=cursor.getString(index3);

            //int cid=cursor.getInt(0);
            // String name=cursor.getString(1);
            //String password=cursor.getString(2);

            buffer.append(cid +" "+name+" "+password+"\n");
        }
            return buffer.toString();
    }

    public String getData(String name,String password){
      //select name, passwor fron uniquetable where name="erick";
        SQLiteDatabase db = helper.getWritableDatabase();

        //select name,password from uniquetable

        //String [] columns={uniqueHelper.UID,uniqueHelper.NAME,uniqueHelper.PASSWORD};
        String [] columns={uniqueHelper.UID};

        // Cursor cursor=db.query(uniqueHelper.TABLE_NAME,columns,uniqueHelper.NAME +" = '"+name+"' ",null,null,null,null);
        String [] selectionArgs={name,password};
        Cursor cursor=db.query(uniqueHelper.TABLE_NAME,columns,
                uniqueHelper.NAME +"=? AND "+uniqueHelper.PASSWORD +"=?",
                selectionArgs,null,null,null,null);

        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(uniqueHelper.UID);
            int personID=cursor.getInt(index1);

            /*int index1 = cursor.getColumnIndex(uniqueHelper.UID);
            int personID=cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(uniqueHelper.NAME);
            String personName = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(uniqueHelper.PASSWORD);
            String personPassword = cursor.getString(index3);*/

            //int cid=cursor.getInt(0);
            // String name=cursor.getString(1);
            //String password=cursor.getString(2);

            buffer.append(personID + "\n");
        }
        return buffer.toString();
    }
    public int update(String oldName,String newName){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(uniqueHelper.NAME,newName);
        String [] whereArgs={oldName};
        int count=db.update(uniqueHelper.TABLE_NAME,contentValues,uniqueHelper.NAME +"=? ",whereArgs);
        return  count;
    }
    public int delete(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] whereArgs={""};
        int count= db.delete(uniqueHelper.TABLE_NAME,uniqueHelper.NAME +"=? ",whereArgs);
        return count;
    }

    static class uniqueHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME="uniqueDatabase";
        private static final String TABLE_NAME="UNIQUETABLE";
        private static final int DATABASE_VERSION=5;
        private static final String UID="_id";
        private static final String NAME="Name";
        private static final String PASSWORD="Password";
        //private static final String ADDRESS="Name";

        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255))";
        private static final String DROP_TABLE="DROP TABLE  IF EXISTS "+TABLE_NAME;
        private Context context;

        public uniqueHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            Message.message(context,"Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create table
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context,"onCreate called");
            }catch (SQLException e){
                Message.message(context,""+ e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Message.message(context,"onUpgrade called");
            }catch (SQLException e){
                Message.message(context,""+ e);
            }
        }
    }
}
