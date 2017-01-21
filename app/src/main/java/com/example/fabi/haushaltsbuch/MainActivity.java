package com.example.fabi.haushaltsbuch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SQLiteHandler db;
    Date currentDate;

    //ToDo Widgets deklarieren

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteHandler(this);
        currentDate = new Date();

        //Value value = new Value();

        //ToDo Widgets init und nen Value in die DB schrieben
    }
}
