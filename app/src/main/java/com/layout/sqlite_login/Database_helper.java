package com.layout.sqlite_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_helper extends SQLiteOpenHelper {

    public Database_helper(@Nullable Context context) {
        super(context, "user_data.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user_detail(name TEXT , email TEXT primary key, age TEXT , password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user_detail");
    }

    public boolean insert_data(String user_name, String user_email, String user_age, String user_password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", user_name);
        contentValues.put("email", user_email);
        contentValues.put("age", user_age);
        contentValues.put("password", user_password);

        long result = db.insert("user_detail", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean update_data(String user_name, String user_email, String user_age, String user_password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", user_name);
        contentValues.put("age", user_age);
        contentValues.put("password", user_password);

        Cursor cursor = db.rawQuery("select * from user_detail where email = ?", new String[]{user_email});

        if (cursor.getCount() > 0) {
            long result = db.update("user_detail", contentValues, "email = ?", new String[]{user_email});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean delete_data(String user_email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("select * from user_detail where email = ?", new String[]{user_email});

        if (cursor.getCount() > 0) {
            long result = db.delete("user_detail", "email=?", new String[]{user_email});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    public Cursor get_data() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user_detail",null);
        return cursor;
    }
}
