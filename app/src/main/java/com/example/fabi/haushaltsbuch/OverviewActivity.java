package com.example.fabi.haushaltsbuch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Fragmet für das anzeigen unserer Buchungen.
 * Zudem wird hier die Gruppierung der Buchungen nach Monat vorgenommen.
 * Created by Fabian on 21.01.2017.
 */

public class OverviewActivity extends Fragment {

    private View addFragmentView;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    private List<Value> values;
    private List<String> Januar = null, Februar = null, März = null,
            April = null, Mai = null, Juni = null,
            Juli = null, August = null, September = null,
            Oktober = null, November = null, Dezember = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addFragmentView = inflater.inflate(R.layout.layout_overview, null);
        listView = (ExpandableListView) addFragmentView.findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(addFragmentView.getContext(), listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        return addFragmentView;
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        values = new LinkedList<>();
        int[] ausgaben = new int[12];

        initMonatsListen();

        values.addAll(((MainActivity)getActivity()).getDb().getAllValues());

        Calendar cal = Calendar.getInstance();

        listDataHeader.add(0, "Keine Einträge.");

        for (Value value : values) {
            cal.setTime(value.getDatum());
            int monat = cal.get(Calendar.MONTH) + 1;
            int Datum;
            String listDataHeaderString = "";

            switch (monat) {
                case 1:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Januar.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 2:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Februar.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 3:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    März.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 4:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    April.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 5:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Mai.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 6:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Juni.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 7:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Juli.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 8:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    August.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 9:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    September.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 10:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Oktober.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 11:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    November.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                case 12:
                    fillListView(ausgaben, monat, value, cal);
                    Datum = cal.get(Calendar.DAY_OF_MONTH);
                    Dezember.add(Datum + ". || " + value.getKategorie() + " || " + value.getBeschreibung() + " " + value.getBetrag() + " €");
                    break;
                default:
            }
        }
        if(!Januar.isEmpty()) listHash.put(listDataHeader.get(0), Januar);
        if(!Februar.isEmpty()) listHash.put(listDataHeader.get(1), Februar);
        if(!März.isEmpty()) listHash.put(listDataHeader.get(2), März);
        if(!April.isEmpty()) listHash.put(listDataHeader.get(3), April);
        if(!Mai.isEmpty()) listHash.put(listDataHeader.get(4), Mai);
        if(!Juni.isEmpty()) listHash.put(listDataHeader.get(5), Juni);
        if(!Juli.isEmpty()) listHash.put(listDataHeader.get(6), Juli);
        if(!August.isEmpty()) listHash.put(listDataHeader.get(7), August);
        if(!September.isEmpty()) listHash.put(listDataHeader.get(8), September);
        if(!Oktober.isEmpty()) listHash.put(listDataHeader.get(9), Oktober);
        if(!November.isEmpty()) listHash.put(listDataHeader.get(10), November);
        if(!Dezember.isEmpty()) listHash.put(listDataHeader.get(11), Dezember);
    }

    private String monthToMonth(int monat) {
        String monatName = null;
        switch (monat) {
            case 1:
                monatName = "Januar";
                break;
            case 2:
                monatName = "Februar";
                break;
            case 3:
                monatName = "März";
                break;
            case 4:
                monatName = "April";
                break;
            case 5:
                monatName = "Mai";
                break;
            case 6:
                monatName = "Juni";
                break;
            case 7:
                monatName = "Juli";
                break;
            case 8:
                monatName = "August";
                break;
            case 9:
                monatName = "September";
                break;
            case 10:
                monatName = "Oktober";
                break;
            case 11:
                monatName = "November";
                break;
            case 12:
                monatName = "Dezember";
                break;
            default:
                monatName = "Monat Unbekannt!";
        }
        return monatName;
    }

    private void fillListView(int[] ausgaben, int monat, Value value, Calendar cal){
        ausgaben[monat] += value.getBetrag();
        String listDataHeaderString = monthToMonth(monat) + ":   (Ausgaben: " + ausgaben[monat] + " €)";
        listDataHeader.set(monat-1, listDataHeaderString);
    }

    private void initMonatsListen(){
        Januar = new ArrayList<>();
        Februar = new ArrayList<>();
        März = new ArrayList<>();
        April = new ArrayList<>();
        Mai = new ArrayList<>();
        Juni = new ArrayList<>();
        Juli = new ArrayList<>();
        August = new ArrayList<>();
        September = new ArrayList<>();
        Oktober = new ArrayList<>();
        November = new ArrayList<>();
        Dezember = new ArrayList<>();
    }
}
