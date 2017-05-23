package com.jonmid.sqlite.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jonmid.sqlite.Helpers.HelperUser;
import com.jonmid.sqlite.Models.User;

import java.util.ArrayList;
import java.util.List;

public class DataUser {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            HelperUser.COLUMN_ID,
            HelperUser.COLUMN_NAME,
            HelperUser.COLUMN_EMAIL
    };

    public DataUser(Context context){
        dbHelper = new HelperUser(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public User create(User user){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_NAME, user.getName());
        values.put(HelperUser.COLUMN_EMAIL, user.getEmail());

        long insertId = database.insert(HelperUser.TABLE_USERS, null, values);

        user.setId(insertId);

        return user;
    }

    public List<User> cursorToList(Cursor cursor){
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMAIL)));

                users.add(user);
            }
        }
        return users;
    }

    public List<User> findAll(){
        Cursor cursor = database.rawQuery("select id,name,email from users", null);
        List<User> users = cursorToList(cursor);
        return users;
    }
}
