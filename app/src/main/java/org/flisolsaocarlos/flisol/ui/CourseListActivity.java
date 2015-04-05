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
import org.flisolsaocarlos.flisol.adapter.CourseAdapter;
import org.flisolsaocarlos.flisol.model.Course;
import org.flisolsaocarlos.flisol.model.Edition;
import org.flisolsaocarlos.flisol.model.HostingPlace;
import org.flisolsaocarlos.flisol.service.CourseService;

import java.util.List;

public class CourseListActivity extends ListActivity {

    private CourseAdapter courseAdapter;
    private CourseService courseService;
    private List<String> years;
    private List<Course> courses;
    private Edition edition;
    private int selectedYear;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setIcon(R.drawable.ic_launcher_white);
        getListView().setDivider(null);
        courseService = new CourseService();
        loadActionBarData();
        listCourses();
    }

    private void loadActionBarData() {
        years = courseService.getYears();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_list, years);
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                String year = years.get(itemPosition);
                //in case the user to select the same year, don't list the courses again
                final int repeatedSelectedYear = Integer.parseInt(year);
                if (repeatedSelectedYear != selectedYear) {
                    selectedYear = Integer.parseInt(year);
                    edition = courseService.getEditionByYear(selectedYear);
                    listCourses();
                }
                return false;
            }
        };
        getActionBar().setListNavigationCallbacks(adapter, navigationListener);
    }

    private void listCourses() {
        final LayoutInflater layoutInfl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        courseAdapter = new CourseAdapter(layoutInfl);
        courses = courseService.getByYear(selectedYear);
        if (courses != null) {
            for (Course course : courses) {
                courseAdapter.addItem(course);
            }
        }
        setListAdapter(courseAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), CourseActivity.class);
        Course course = courseAdapter.getItem(position);
        intent.putExtra("course", course);

        final HostingPlace hostingPlace = courseService.getHostingPlaceByEdition(edition);
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
