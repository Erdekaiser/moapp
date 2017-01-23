package com.example.fabi.haushaltsbuch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OverviewActivity extends Fragment {

    private View addFragmentView;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    private HashMap<Integer, Float> betragSummen;

    private AppCompatActivity mainContext;
    private SQLiteHandler db;
    private int jahr;

    OverviewActivity(){}

    OverviewActivity(AppCompatActivity mainContext, SQLiteHandler db){
        this.mainContext = mainContext;
        this.db = db;
    }


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

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        betragSummen = new HashMap<Integer, Float>();


        List<Value> values = new LinkedList<>();
        values.addAll(db.getAllValues());

        List<ListHeaderValue> lhValues = new LinkedList<>();

        Calendar cal = Calendar.getInstance();

        //Objekte gruppieren und sortieren

        for(Value value : values){
            cal.setTime(value.getDatum());
            int monat = cal.get(Calendar.MONTH)+1;

            switch(monat){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                default:
            }
        }





        for(Value value : values){
            cal.setTime(value.getDatum());
            listDataHeader.add(monthToMonth(cal.get(Calendar.MONTH)+1));
        }



        //ToDo mit Tatsächlichen Daten füllen

        //listDataHeader.add("2017");
        //listDataHeader.add("2016");


        //List<String> Content = new ArrayList<>();
        //Content.add("Milch");

        //List<String> Februar = new ArrayList<>();
        //Februar.add("Game1");
        //Februar.add("Game2");
        //Februar.add("Game3");
        //Februar.add("Game4");

        //listHash.put(listDataHeader.get(0), Content);
        //listHash.put(listDataHeader.get(1), Februar);
    }

    private String monthToMonth(int monat){
        String monatName = null;
        switch(monat) {
            case 1: monatName = "Januar";
                    break;
            case 2: monatName = "Februar";
                    break;
            case 3: monatName = "März";
                    break;
            case 4: monatName = "April";
                    break;
            case 5: monatName = "Mai";
                    break;
            case 6: monatName = "Juni";
                    break;
            case 7: monatName = "Juli";
                    break;
            case 8: monatName = "August";
                    break;
            case 9: monatName = "September";
                    break;
            case 10:    monatName = "Oktober";
                        break;
            case 11:    monatName = "November";
                        break;
            case 12:    monatName = "Dezember";
                        break;
            default: monatName = "Monat Unbekannt!";
        }
        return monatName;
    }
}
