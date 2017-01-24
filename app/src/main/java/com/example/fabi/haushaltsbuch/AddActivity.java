package com.example.fabi.haushaltsbuch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class AddActivity extends Fragment implements AdapterView.OnItemClickListener{

    private Date currentDate;
    private Value value;
    private List<Value> values;

    private String[] KATEGORIEN = new String[]{"Games", "Movies", "Lebensmittel", "Haushalt", "Tanken", "Fixkosten"};

    private ListView lvKategorien = null;
    private EditText txtInputBetrag = null;
    private EditText txtInputBeschreibung = null;
    private TextView txtOutputValue = null;

    private View addFragmentView;
    private AppCompatActivity mainContext;
    private SQLiteHandler db;

    AddActivity(){}

    AddActivity(AppCompatActivity mainContext, SQLiteHandler db){
        this.mainContext = mainContext;
        this.db = db;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        addFragmentView = inflater.inflate(R.layout.layout_add, null);

        currentDate = new Date();

        txtInputBetrag = (EditText) addFragmentView.findViewById(R.id.txtInput_Betrag);

        txtInputBeschreibung = (EditText) addFragmentView.findViewById(R.id.txtInput_Beschreibung);
        txtOutputValue = (TextView) addFragmentView.findViewById(R.id.txtOutput_Added);

        String introText = "<b> Wilkommen beim Haushaltsbuch für Gamer! </b> <br>" +
                "Um einen neuen Eintrag anzulegen tragen Sie bitte einen Betrag ein und klicken anschließend auf eine Kategorie.<br>" +
                "Das hinzufügen einer Beschreibung ist optional.";

        txtOutputValue.setText(Html.fromHtml(introText));

        //ToDo Icons auf die Liste
        //ToDo mehr Kategorien

        lvKategorien = (ListView) addFragmentView.findViewById(R.id.list_kategorie);

        ArrayAdapter<String> lvAdapter = new ArrayAdapter<>(mainContext, android.R.layout.simple_expandable_list_item_1, KATEGORIEN);
        lvKategorien.setAdapter(lvAdapter);
        lvKategorien.setOnItemClickListener(this);

        return addFragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tmpBetrag = txtInputBetrag.getText().toString();
        String tmpBeschreibung = txtInputBeschreibung.getText().toString();
        String tmpTxtAusgabe;

        if(tmpBeschreibung.equals("")){
            tmpBeschreibung = "Ohne Beschreibung.";
        }

        if(tmpBetrag.equals("")){
            Toast.makeText(mainContext, "Bitte einen Wert bei Betrag eintragen!", Toast.LENGTH_LONG).show();
        }else{
            tmpTxtAusgabe =
                    "Hinzugefügt:\n" +
                    "Betrag:\t" + tmpBetrag + "\n" +
                    "Beschreibung:\t" + tmpBeschreibung + "\n" +
                    "Kategorie:\t" + KATEGORIEN[position];
            txtOutputValue.setText(tmpTxtAusgabe);
            value = new Value(0, currentDate, tmpBeschreibung, Float.valueOf(tmpBetrag), KATEGORIEN[position]);

            db.addValue(value);

            txtInputBetrag.setText("");
            txtInputBeschreibung.setText("");
        }
    }
}
