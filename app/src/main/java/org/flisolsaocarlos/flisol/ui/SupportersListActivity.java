package org.flisolsaocarlos.flisol.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.adapter.SupporterAdapter;
import org.flisolsaocarlos.flisol.model.Supporter;
import org.flisolsaocarlos.flisol.service.SupporterService;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SupportersListActivity extends ListActivity {

    private SupporterService service;
    private SupporterAdapter adapter;
    private Supporter supporter;
    private Crouton crouton;

    //TODO update supporter data
    // this selects the current year, purposely not programmatically
    private int currentYear = 2015;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        service = new SupporterService(this);
        final LayoutInflater layoutInfl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final AssetManager assets = getAssets();
        adapter = new SupporterAdapter(layoutInfl, assets);
        final List<Supporter> supporters = service.getByYear(currentYear);
        if (supporters != null) {
            for (Supporter supporter : supporters) {
                adapter.addItem(supporter);
            }
        }
        setListAdapter(adapter);

        if (supporters.isEmpty()) {
            userFeedback();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id) {
        supporter = adapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Resources res = getResources();
        builder.setMessage(res.getString(R.string.msg_website_supporter))
                .setPositiveButton(res.getString(R.string.msg_yes), dialogClickListener)
                .setNegativeButton(res.getString(R.string.msg_no), dialogClickListener).show();
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int option) {
            if (option == DialogInterface.BUTTON_POSITIVE) {
                openWebsite(supporter);
            }
        }
    };

    public void openWebsite(final Supporter supporter) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), WebActivity.class);
        intent.putExtra("url", supporter.getWebsite());
        intent.putExtra("title", supporter.getName());
        startActivity(intent);
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
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
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
