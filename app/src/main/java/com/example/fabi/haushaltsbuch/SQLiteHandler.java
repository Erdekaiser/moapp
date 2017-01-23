package com.example.fabi.haushaltsbuch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Klasse für den Zugriff auf die SQLite Datenbank
 *
 * Created by Fabian on 21.01.2017.
 */

class SQLiteHandler extends SQLiteOpenHelper{

    private static final String TABLE_BALANCE = "balance";

    private static final String KEY_ID = "id";
    private static final String KEY_DATUM = "datum";
    private static final String KEY_BESCHREIBUNG = "beschreibung";
    private static final String KEY_BETRAG = "betrag";
    private static final String KEY_KATEGORIE = "kategorie";

    private static final String[] COLUMS = {KEY_ID, KEY_DATUM, KEY_BESCHREIBUNG, KEY_BETRAG, KEY_KATEGORIE};

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "HaushaltsbuchDB";

    SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BALANCE_TABLE = "CREATE TABLE " + TABLE_BALANCE +
                " ( " +
                KEY_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_DATUM           + " DATE, " +
                KEY_BESCHREIBUNG    + " TEXT, " +
                KEY_BETRAG          + " DECIMAL(10,2), " +
                KEY_KATEGORIE       + " TEXT" +
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

    void addValue(Value value){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATUM, value.getDatum().toString());
        values.put(KEY_BESCHREIBUNG, value.getBeschreibung());
        values.put(KEY_BETRAG, value.getBetrag().toString());
        values.put(KEY_KATEGORIE, value.getKategorie());

        Log.d("Add Value: " , values.toString());
        db.insert(TABLE_BALANCE, null, values);

        db.close();
    }

    public Value getValue(int id) {
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
        assert cursor != null;
        value.setId(Integer.parseInt(cursor.getString(0)));
        value.setDatum(castStringtoDate(cursor.getString(1)));
        value.setBeschreibung(cursor.getString(2));
        value.setBetrag(Float.valueOf(cursor.getString(3)));
        value.setKategorie(cursor.getString(4));

        cursor.close();
        return value;
    }

    public List<Value> getAllValues(){
        Value value;
        List<Value> values = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_BALANCE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                value = new Value();
                value.setId(Integer.parseInt(cursor.getString(0)));
                value.setDatum(castStringtoDate(cursor.getString(1)));
                value.setBeschreibung(cursor.getString(2));
                value.setBetrag(Float.valueOf(cursor.getString(3)));
                value.setKategorie(cursor.getString(4));
                values.add(value);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return values;
    }

    //Feed it a Year and it shall return the SUM for each Month in this Year.
    /*public HashMap<Integer, Float> getAllMonth(int jahr){
        Integer monat;
        Float betragSum;
        HashMap<Integer, Float> betragsSummen = new HashMap<>();
        String[] Colums = new String[]{"CAST(strftime('%m', " + KEY_DATUM + ") AS INTEGER)", "SUM(" + KEY_BETRAG + ")"};
        String selection = "strftime('%Y', " + KEY_DATUM + ")=?";
        String[] arguments = new String[]{String.valueOf(jahr)};
        String groupBy = "CAST(strftime('%m', " + KEY_DATUM + ") AS INTEGER)";
        String having = null;
        String orderBY = "CAST(strftime('%m', " + KEY_DATUM + ") AS INTEGER) ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(
                        TABLE_BALANCE,
                        Colums,
                        null,
                        null,
                        groupBy,
                        having,
                        orderBY);

        if(cursor != null) {
            cursor.moveToFirst();
            do {
                monat = new Integer(Integer.parseInt(cursor.getString(0)));
                betragSum = new Float(Float.valueOf(cursor.getString(1)));
                betragsSummen.put(monat, betragSum);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return betragsSummen;
    }
    */

    public void deleteValue(Value value){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BALANCE,
                KEY_ID + " =  ?",
                new String[]{String.valueOf(value.getId())});
        db.close();
    }

    private Date castStringtoDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
