package com.example.duniamusik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String database_name = "db_dunia_musik";
    public static final String table_name = "tb_user";
    public static final String row_email = "email";
    public static final String row_username = "username";
    public static final String row_phone = "phone";
    public static final String row_password = "password";
    private final SQLiteDatabase db;
    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_user(username TEXT PRIMARY KEY, email TEXT, phone TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tb_user");
    }

    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    public boolean checkUser(String username, String password){
        String[] columns = {row_username};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("tb_user", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public boolean checkEmail(String email){
        String[] columns = {row_username};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_email + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("tb_user", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean checkUsername(String username){
        String[] columns = {row_username};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query("tb_user", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
