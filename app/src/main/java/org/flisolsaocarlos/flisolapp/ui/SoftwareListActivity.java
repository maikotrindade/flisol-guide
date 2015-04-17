package org.flisolsaocarlos.flisolapp.ui;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import org.flisolsaocarlos.flisolapp.R;
import org.flisolsaocarlos.flisolapp.adapter.SoftwareAdapter;
import org.flisolsaocarlos.flisolapp.model.Software;
import org.flisolsaocarlos.flisolapp.service.SoftwareService;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SoftwareListActivity extends ListActivity {

    SoftwareAdapter adapter;
    SoftwareService service;
    List<Software> softwares;
    private Crouton crouton;

    //TODO update software data
    // this selects the current year, purposely not programmatically
    private int year = 2015;

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
        if (softwares != null || softwares.isEmpty()) {
            for (Software software : softwares) {
                adapter.addItem(software);
            }
        } else {
            userFeedback();
        }

        setListAdapter(adapter);
    }

    public void userFeedback() {
        final String backgroundColor = "#EF6C00";
        final int textSize = 18;

        Style style = new Style.Builder()
                .setBackgroundColorValue(Color.parseColor(backgroundColor))
                .setTextSize(textSize)
                .setConfiguration(new Configuration.Builder().setDuration(Configuration.DURATION_INFINITE).build())
                .build();

        crouton = Crouton.makeText(this, getResources().getString(R.string.coming_soon), style);
        crouton.show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
