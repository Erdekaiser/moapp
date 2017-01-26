package com.example.fabi.haushaltsbuch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Fragmet für das hinzufügen von Buchungen.
 * Sowie Feedback über erfolgreiches hinzufügen.
 * Created by Fabian on 21.01.2017.
 */

public class AddActivity extends Fragment implements AdapterView.OnItemClickListener{

    private Date currentDate;
    private Value value;
    private CustomList clAdapter;

    private String[] KATEGORIEN = new String[]{"Games", "Movies", "Lebensmittel", "Haushalt", "Tanken", "Fixkosten"};
    private Integer[] IMAGE_ID = {R.drawable.ic_game_cat, R.drawable.ic_movie_cat, R.drawable.ic_food_cat, R.drawable.ic_household_cat, R.drawable.ic_gas_cat, R.drawable.ic_fix_cat};

    private ListView lvKategorien = null;
    private EditText txtInputBetrag = null;
    private EditText txtInputBeschreibung = null;
    private TextView txtOutputValue = null;
    private ImageView txtOutputSuccess = null;

    private View addFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        addFragmentView = inflater.inflate(R.layout.layout_add, null);

        currentDate = new Date();

        txtInputBetrag = (EditText) addFragmentView.findViewById(R.id.txtInput_Betrag);

        txtInputBeschreibung = (EditText) addFragmentView.findViewById(R.id.txtInput_Beschreibung);
        txtOutputValue = (TextView) addFragmentView.findViewById(R.id.txtOutput_Added);
        txtOutputSuccess = (ImageView) addFragmentView.findViewById(R.id.txtOutput_Added_Success);

        String introText =
                "<b> Wilkommen beim Haushaltsbuch für Gamer! </b> <br>" +
                "Um einen neuen Eintrag anzulegen tragen Sie bitte einen Betrag ein und klicken anschließend auf eine Kategorie.<br>" +
                "Das hinzufügen einer Beschreibung ist optional.";

        txtOutputValue.setText(Html.fromHtml(introText));

        clAdapter = new CustomList(getActivity(), KATEGORIEN, IMAGE_ID);
        lvKategorien = (ListView) addFragmentView.findViewById(R.id.list_kategorie);
        lvKategorien.setAdapter(clAdapter);
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
            Toast.makeText(getActivity(), "Bitte einen Wert bei Betrag eintragen!", Toast.LENGTH_LONG).show();
        }else{
            tmpTxtAusgabe =
                    "<b>Erfolgreich hinzugefügt!</b><br>" +
                    "Betrag: " + tmpBetrag + " €<br>" +
                    "Beschreibung: " + tmpBeschreibung + "<br>" +
                    "Kategorie: " + KATEGORIEN[position];

            value = new Value(0, currentDate, tmpBeschreibung, Float.valueOf(tmpBetrag), KATEGORIEN[position]);

            ((MainActivity)getActivity()).getDb().addValue(value);
            ((MainActivity)getActivity()).getAdapter().notifyDataSetChanged();
            ((MainActivity)getActivity()).setTabIcons();

            txtOutputValue.setText(Html.fromHtml(tmpTxtAusgabe));
            txtOutputSuccess.setImageResource(R.drawable.ic_success);

            txtInputBetrag.setText("");
            txtInputBeschreibung.setText("");
        }
    }
}

