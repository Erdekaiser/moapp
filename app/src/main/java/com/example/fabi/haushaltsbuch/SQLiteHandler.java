package com.example.fabi.haushaltsbuch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Fabian on 21.01.2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper{

    private static final String TABLE_BALANCE = "balance";

    private static final String KEY_ID = "id";
    private static final String KEY_DATUM = "datum";
    private static final String KEY_BESCHREIBUNG = "beschreibung";
    private static final String KEY_BETRAG = "betrag";
    private static final String KEY_KATEGORIE = "kategorie";

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "HaushaltsbuchDB";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BALANCE_TABLE = "CREATE TABLE" + TABLE_BALANCE +
                " ( " +
                KEY_ID              + " INT PRIMARY KEY AUTOINCREMENT, " +
                KEY_DATUM           + " DATETIME NOT NULL, " +
                KEY_BESCHREIBUNG    + " TEXT, " +
                KEY_BETRAG          + " DECIMAL(10,2) NOT NULL, " +
                KEY_KATEGORIE       + " TEXT NOT NULL" +
                " )";

        Log.d("Create Table: " , CREATE_BALANCE_TABLE);
        db.execSQL(CREATE_BALANCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //addValue

    //getValue

    //getAllValues

    //updateValue

    //deleteValue
}
