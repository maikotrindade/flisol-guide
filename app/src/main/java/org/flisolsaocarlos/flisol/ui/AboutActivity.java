package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.flisolsaocarlos.flisol.R;

public class AboutActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        Button licenseButton = (Button) findViewById(R.id.licenseBtn);
        licenseButton.setOnClickListener(this);

        Button aboutButton = (Button) findViewById(R.id.aboutBtn);
        aboutButton.setOnClickListener(this);

        Button githubButton = (Button) findViewById(R.id.githubBtn);
        githubButton.setOnClickListener(this);

        Button websiteButton = (Button) findViewById(R.id.websiteBtn);
        websiteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW);

        switch (view.getId()) {
            case R.id.licenseBtn:
                final String licenseUrl = getResources().getString(R.string.license_link);
                i.setData(Uri.parse(licenseUrl));
                startActivity(i);
                break;
            case R.id.aboutBtn:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AboutAppActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.start_in, R.anim.start_out);
                break;
            case R.id.githubBtn:
                final String githubUrl = getResources().getString(R.string.github_project_link);
                i.setData(Uri.parse(githubUrl));
                startActivity(i);
                break;
            case R.id.websiteBtn:
                final String websiteUrl = getResources().getString(R.string.official_website_link);
                i.setData(Uri.parse(websiteUrl));
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
