package org.flisolsaocarlos.flisol.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.MenuItem;

import org.flisolsaocarlos.flisol.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AgendaActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private Crouton crouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
                AgendaRoomOneFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
                AgendaRoomTwoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Tab3"),
                AgendaRoomThreeFragment.class, null);
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
    public void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
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
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }
}