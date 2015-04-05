package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import org.flisolsaocarlos.flisol.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AgendaActivity extends Activity {

    private Crouton crouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        //TODO implement schedule and remove this message
        userFeedback();
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
