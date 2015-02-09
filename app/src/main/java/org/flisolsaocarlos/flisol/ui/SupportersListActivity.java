package org.flisolsaocarlos.flisol.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
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

public class SupportersListActivity extends ListActivity {

    private SupporterService service;
    private SupporterAdapter adapter;
    private Supporter supporter;
    //TODO update supporter data and delete the dummy data from JSON
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
