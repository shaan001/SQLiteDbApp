package com.hybrid.tech.sqlitedbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admins on 4/24/2016.
 */
public class NameSaveHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME = "NAMES";
    private static final int DB_VERSION = 1;

    public NameSaveHelper(Context context) {

        super(context,DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ALLNAMES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT );");

        insertAllNames(db, "Shivam");
        insertAllNames(db, "Yash");
    }

    private void insertAllNames(SQLiteDatabase db, String names) {
        ContentValues name = new ContentValues();
        name.put("NAME", names);
        db.insert("ALLNAMES", null, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
