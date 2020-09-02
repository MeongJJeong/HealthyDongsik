package com.example.dongsik.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpcsDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DBFILE_CONTACT = "spcs.db";

    public SpcsDbHelper(Context context){
        super(context,DBFILE_CONTACT,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE spcsTbl (sName CHAR(50) PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS spcsTbl");
        onCreate(db);
    }
}
