package com.example.lab06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
   private static final String DATABASE_NAME="MyDB";
   private static final int DATABASE_VERSION=1;
   private static final String TABLE_NAME="Name";
   private static final String KEY_NAME="name";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = String.format("create table %s(id integer primary key, %s text)", TABLE_NAME, KEY_NAME);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = String.format("drop table if exists %s", TABLE_NAME);
        db.execSQL(deleteTable);
    }
    public void addValue(String value){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,value);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public void removeValue(String value){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, KEY_NAME + " = ?", new String[]{value});
    }
    public List<String> getAll(){
        List<String> result = new ArrayList<>();

        String query = "select * from " + TABLE_NAME;

        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            result.add(cursor.getString(1));
            cursor.moveToNext();
        }

        return result;
    }
}
