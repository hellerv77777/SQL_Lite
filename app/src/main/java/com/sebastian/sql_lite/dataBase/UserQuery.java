package com.sebastian.sql_lite.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserQuery {


    public static void insertUser(Context context,String name, String lastName, String email, String pass){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();


        db.execSQL("INSERT INTO '"+UtilsDb.TABLE_USERS+"' (name,lastName,email,pass)  VALUES ('"+name+"','"+lastName+"','"+email+"','"+pass+"') ");
        db.close();


    }

    public static void updateUser(Context context,String name, String lastName, String email, String pass, String id){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();

        db.execSQL("UPDATE'"+UtilsDb.TABLE_USERS+"' SET name = '"+name+"', lastName = '"+lastName+"', email = '"+email+"', pass = '"+pass+"'  WHERE id = '"+id+"'");
    }

    public static List<User> getListUser(Context context){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();

        List<User> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM '"+UtilsDb.TABLE_USERS+"'",null);

        while (cursor.moveToNext()){

            String id=cursor.getString(cursor.getColumnIndex("id"));
            String name =cursor.getString(cursor.getColumnIndex("name"));
            String lastName=cursor.getString(cursor.getColumnIndex("lastName"));

            userList.add(new User(id,name,lastName));
        }
        cursor.close();
        db.close();
        return userList;
    }

    public static List<User> getListUserByCharacter(Context context, String character){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();

        List<User> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM '"+UtilsDb.TABLE_USERS+"' WHERE name like '%"+character +"%' OR lastName like '%"+character +"%'",null);
        while (cursor.moveToNext()){

            String id=cursor.getString(cursor.getColumnIndex("id"));
            String name =cursor.getString(cursor.getColumnIndex("name"));
            String lastName=cursor.getString(cursor.getColumnIndex("lastName"));

            userList.add(new User(id,name,lastName));
        }
        cursor.close();
        db.close();
        return userList;
    }

    public static User getDataUserById(Context context, String id){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM '"+UtilsDb.TABLE_USERS+"' WHERE id = '"+id +"'",null);

        if (cursor.moveToFirst()){


            String name =cursor.getString(cursor.getColumnIndex("name"));
            String lastName=cursor.getString(cursor.getColumnIndex("lastName"));
            String pass=cursor.getString(cursor.getColumnIndex("pass"));
            String email=cursor.getString(cursor.getColumnIndex("email"));

            return  new User(name,lastName,email,pass);
        }
        cursor.close();
        db.close();
        return null;


    }

    public static void delete(Context context, String id){

        DataBaseSqLite dataBaseSqLite = new DataBaseSqLite(context,null);
        SQLiteDatabase db = dataBaseSqLite.getWritableDatabase();

        db.execSQL("DELETE FROM '"+UtilsDb.TABLE_USERS+"' WHERE id = '"+id+"'");
        db.close();

    }


}
