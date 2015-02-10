package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.BuildConfig;
import org.flisolsaocarlos.flisol.R;

public class AboutAppActivity extends Activity implements View.OnClickListener{

    private TextView versionNameTxt;
    private Button licenseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        versionNameTxt = (TextView) findViewById(R.id.versionName);
        final String versionName = BuildConfig.VERSION_NAME;
        versionNameTxt.setText(versionName);

        licenseBtn = (Button) findViewById(R.id.licenseBtn);
        licenseBtn.setOnClickListener(this);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.licenseBtn:
                Intent i = new Intent(Intent.ACTION_VIEW);
                final String licenseUrl = getResources().getString(R.string.license_link);
                i.setData(Uri.parse(licenseUrl));
                startActivity(i);
                break;
        }
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
