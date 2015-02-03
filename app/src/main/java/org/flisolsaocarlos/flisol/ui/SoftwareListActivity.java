package org.flisolsaocarlos.flisol.ui;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.adapter.SoftwareAdapter;
import org.flisolsaocarlos.flisol.model.Software;
import org.flisolsaocarlos.flisol.service.SoftwareService;

import java.util.List;

public class SoftwareListActivity extends ListActivity {

    SoftwareAdapter adapter;
    SoftwareService service;
    List<Software> softwares;
    //TODO update year
    // this selects the current year, purposely not programmatically
    private int year = 2014;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);
        getListView().setDivider(null);
        listSoftwares();
    }

    private void listSoftwares() {
        service = new SoftwareService();
        final LayoutInflater layoutInfl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter = new SoftwareAdapter(layoutInfl);
        softwares = service.getByYear(year);
        if (softwares != null) {
            for (Software software : softwares) {
                adapter.addItem(software);
            }
        }
        setListAdapter(adapter);
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
