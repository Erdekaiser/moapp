package com.example.fabi.haushaltsbuch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, InputType {

    private SQLiteHandler db;
    private Date currentDate;
    private Value value;

    private String[] KATEGORIEN = new String[]{"Games", "Movies", "Lebensmittel", "Haushalt", "Tanken", "Fixkosten"};

    private ListView lvKategorien = null;
    private EditText txtInputBetrag = null;
    private EditText txtInputBeschreibung = null;
    private TextView txtOutputValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteHandler(this);

        currentDate = new Date();

        txtInputBetrag = (EditText) findViewById(R.id.txtInput_Betrag);
        //txtInputBetrag.setRawInputType(Configuration.KEYBOARD_12KEY);

        txtInputBeschreibung = (EditText) findViewById(R.id.txtInput_Beschreibung);
        txtOutputValue = (TextView) findViewById(R.id.txtOutput_Added);
        lvKategorien = (ListView) findViewById(R.id.list_kategorie);

        ArrayAdapter<String> lvAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, KATEGORIEN);
        lvKategorien.setAdapter(lvAdapter);
        lvKategorien.setOnItemClickListener(this);

        //ToDo einen Value in die DB schrieben
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tmpBetrag = txtInputBetrag.getText().toString();
        String tmpBeschreibung = txtInputBeschreibung.getText().toString();
        String tmpTxtAusgabe = null;

        if(tmpBeschreibung.equals("")){
            tmpBeschreibung = "Ohne Beschreibung.";
        }

        if(tmpBetrag.equals("")){
            Toast.makeText(this.getApplicationContext(), "Bitte einen Wert bei Betrag eintragen!", Toast.LENGTH_LONG).show();
        }else{
            tmpTxtAusgabe = "Hinzugefügt:\n" +
                            "Betrag:\t" + tmpBetrag + "\n" +
                            "Beschreibung:\t" + tmpBeschreibung + "\n" +
                            "Kategorie:\t" + KATEGORIEN[position];
            txtOutputValue.setText(tmpTxtAusgabe);
            value = new Value(0, currentDate, tmpBeschreibung, Float.valueOf(tmpBetrag), KATEGORIEN[position]);
            System.out.println( "ID:\t" + value.getId() + "\n" +
                                "Betrag:\t" + value.getBetrag() + "\n" +
                                "Beschreibung:\t " + value.getBeschreibung() + "\n" +
                                "Datum:\t" + value.getDatum() + "\n" +
                                "Kategorie:\t" + value.getKategorie());
            db.addValue(value);
            txtInputBetrag.setText("");
            txtInputBeschreibung.setText("");
        }
    }
}
