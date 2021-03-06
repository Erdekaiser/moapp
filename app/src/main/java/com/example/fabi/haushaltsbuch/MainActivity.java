package com.example.fabi.haushaltsbuch;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Unsere einzige Activity und Haupteinstiegspunkt in die App.
 * Hier wird eine Appbar inkl. Tabs und ViewPager erstellt welche über Tabs Fragmets verwalten kann.
 * Zudem findet hier die initialisierung des Datenbank Objektes statt.
 * Created by Fabian on 21.01.2017.
 */

public class MainActivity extends AppCompatActivity {

    private SQLiteHandler db;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabs;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new SQLiteHandler(this);

        //ToolbarLayout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //TabLayout
        tabs = (TabLayout) findViewById(R.id.tabs);
        if (tabs != null) {
            tabs.setupWithViewPager(viewPager);
        }
        setTabIcons();
    }

    private void setupViewPager(final ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new AddActivity(), "Add");
        adapter.addFragment(new OverviewActivity(), "Overview");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setIcon(R.drawable.ic_logo);
        return true;
    }

    //OptionsMenu Item "Wipe Data" inkl. Alert Dialog.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Wipe Data")
                        .setMessage("Es werden alle Einträge gelöscht. Sind Sie sicher?")
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteList();
                            }

                        })
                        .setNegativeButton("Nein", null)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Adapter getAdapter(){
        return adapter;
    }

    public void setTabIcons(){
        tabs.getTabAt(0).setIcon(R.drawable.ic_add);
        tabs.getTabAt(1).setIcon(R.drawable.ic_overview);
    }

    //Löscht die komplette Value Liste
    public void deleteList(){
        List<Value> values = new LinkedList<>();
        values.addAll(db.getAllValues());

        for(Value val : values) {
            db.deleteValue(val);
        }
        getAdapter().notifyDataSetChanged();
        setTabIcons();
    }

    public SQLiteHandler getDb() {
        return db;
    }
}
