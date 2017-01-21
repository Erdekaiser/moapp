package com.example.fabi.haushaltsbuch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.Currency;

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

    private static final String[] COLUMS = {KEY_ID, KEY_DATUM, KEY_BESCHREIBUNG, KEY_BETRAG, KEY_KATEGORIE};

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
                KEY_DATUM           + " DATE NOT NULL, " +
                KEY_BESCHREIBUNG    + " TEXT, " +
                KEY_BETRAG          + " DECIMAL(10,2) NOT NULL, " +
                KEY_KATEGORIE       + " TEXT NOT NULL" +
                " )";

        Log.d("Create Table: " , CREATE_BALANCE_TABLE);
        db.execSQL(CREATE_BALANCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropExisting = "DROP TABLE IF EXISTS " + TABLE_BALANCE;
        db.execSQL(dropExisting);

        this.onCreate(db);
    }

    public void addValue(Value value){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATUM, value.getDatum().toString());
        values.put(KEY_BESCHREIBUNG, value.getBeschreibung());
        values.put(KEY_BETRAG, value.getBetrag().toString());
        values.put(KEY_KATEGORIE, value.getKategorie());

        db.insert(TABLE_BALANCE, null, values);

        db.close();
    }

    public Value getValue(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_BALANCE,
                        COLUMS,
                        " id = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Value value = new Value();
        value.setId(Integer.parseInt(cursor.getString(0)));
        value.setDatum(Date.valueOf(cursor.getString(1)));
        value.setBeschreibung(cursor.getString(2));
        value.setBetrag(Currency.getInstance(cursor.getString(3)));
        value.setKategorie(cursor.getString(4));

        return value;
    }

    //getAllValues

    //updateValue

    //deleteValue
}
