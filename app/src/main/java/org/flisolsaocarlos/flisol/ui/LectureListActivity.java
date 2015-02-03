package org.flisolsaocarlos.flisol.ui;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.adapter.LectureAdapter;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.service.LectureService;

import java.util.List;

public class LectureListActivity extends ListActivity {

    private final String[] YEARS = new String[]{
            "2014",
            "2013",
            "2012"
    };
    private LectureAdapter lectureAdapter;
    private LectureService lectureService;
    private List<Lecture> lectures;
    //TODO update year
    // this selects the current year, purposely not programmatically
    private int selectedYear = 2014;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);
        getListView().setDivider(null);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_list, YEARS);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                String year = YEARS[itemPosition];
                //in case of the user to select the same year, don't generate the lectures again
                final int repeatedSelectedYear = Integer.parseInt(year);
                if (repeatedSelectedYear != selectedYear) {
                    selectedYear = Integer.parseInt(year);
                    listLectures();
                }
                return false;
            }
        };
        getActionBar().setListNavigationCallbacks(adapter, navigationListener);

        listLectures();
    }

    private void listLectures() {
        //TODO
        lectureService = new LectureService();
        final LayoutInflater layoutInfl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lectureAdapter = new LectureAdapter(layoutInfl);
        lectures = lectureService.getByYear(selectedYear);
        if ((lectures != null) && (!lectures.isEmpty())) {
            for (Lecture lecture : lectures) {
                lectureAdapter.addItem(lecture);
            }
            setListAdapter(lectureAdapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), LectureActivity.class);
        Lecture lecture = lectureAdapter.getItem(position);
        intent.putExtra("lecture", lecture);
        startActivity(intent);
        overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }
}
