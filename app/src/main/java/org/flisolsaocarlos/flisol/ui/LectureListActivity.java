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
import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.model.Lecture;
import org.flisolsaocarlos.flisol.service.LectureService;

import java.util.List;

public class LectureListActivity extends ListActivity {

    private LectureAdapter lectureAdapter;
    private LectureService lectureService;
    private List<String> years;
    private List<Lecture> lectures;
    private Edition edition;
    private int selectedYear;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setIcon(R.drawable.ic_launcher_white);
        getListView().setDivider(null);

        lectureService = new LectureService();
        loadActionBarData();
        listLectures();
    }

    private void loadActionBarData() {
        years = lectureService.getYears();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_list, years);
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                String year = years.get(itemPosition);
                //in case the user to select the same year, don't list the courses again
                final int repeatedSelectedYear = Integer.parseInt(year);
                if (repeatedSelectedYear != selectedYear) {
                    selectedYear = Integer.parseInt(year);
                    edition = lectureService.getEditionByYear(selectedYear);
                    listLectures();
                }
                return false;
            }
        };
        getActionBar().setListNavigationCallbacks(arrayAdapter, navigationListener);
    }

    private void listLectures() {
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

        final HostingPlace hostingPlace = lectureService.getHostingPlaceByEdition(edition);
        final String hostingPlaceName = hostingPlace.getName();
        intent.putExtra("hostingPlace", hostingPlaceName);

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
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }
}
