package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.service.SoftwareService;

public class InstallFestActivity extends Activity implements View.OnClickListener {

    private SoftwareService service;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_fest_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        Button softwareListBtn = (Button) findViewById(R.id.softwaresBtn);
        softwareListBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SoftwareListActivity.class);
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
