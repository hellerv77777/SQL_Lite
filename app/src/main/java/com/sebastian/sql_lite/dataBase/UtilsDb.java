package com.sebastian.sql_lite.dataBase;

public class UtilsDb {

    //Nombre de la base de datos
    public static final String DB_NAME =  "crud";
    //Version de la base de datos
    public static final String TABLE_USERS = "users";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users " +
            "( id INTEGER PRIMARY KEY, name TEXT, lastName TEXT, email TEXT, pass TEXT ) ";
}
