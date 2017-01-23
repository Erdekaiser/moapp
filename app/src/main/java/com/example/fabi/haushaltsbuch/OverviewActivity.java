package com.example.fabi.haushaltsbuch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OverviewActivity extends Fragment {

    private View addFragmentView;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

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

        listDataHeader.add("2017");
        listDataHeader.add("2016");


        List<String> Januar = new ArrayList<>();
        Januar.add("Milch");

        List<String> Februar = new ArrayList<>();
        Februar.add("Game1");
        Februar.add("Game2");
        Februar.add("Game3");
        Februar.add("Game4");

        listHash.put(listDataHeader.get(0), Januar);
        listHash.put(listDataHeader.get(1), Februar);
    }
}
