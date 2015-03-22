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

        private Button contactBtn, aboutBtn, websiteBtn,
                interWebsiteBtn, gitoriousBtn, githubBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);

        websiteBtn = (Button) findViewById(R.id.localWebsiteBtn);
        websiteBtn.setOnClickListener(this);

        contactBtn = (Button) findViewById(R.id.contactBtn);
        contactBtn.setOnClickListener(this);

        interWebsiteBtn = (Button) findViewById(R.id.interWebsiteBtn);
        interWebsiteBtn.setOnClickListener(this);

        gitoriousBtn = (Button) findViewById(R.id.gitoriousBtn);
        gitoriousBtn.setOnClickListener(this);

        githubBtn = (Button) findViewById(R.id.githubBtn);
        githubBtn.setOnClickListener(this);
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
            case R.id.localWebsiteBtn:
                final String websiteUrl = getResources().getString(R.string.hosting_place_website_link);
                i.setData(Uri.parse(websiteUrl));
                startActivity(i);
                break;
            case R.id.interWebsiteBtn:
                final String interWebsiteUrl = getResources().getString(R.string.international_website_link);
                i.setData(Uri.parse(interWebsiteUrl));
                startActivity(i);
                break;
            case R.id.contactBtn:
                final String contactUrl = getResources().getString(R.string.contact_flisol_url);
                i.setData(Uri.parse(contactUrl));
                startActivity(i);
                break;
            case R.id.gitoriousBtn:
                final String gitoriousUrl = getResources().getString(R.string.gitorious_project_link);
                i.setData(Uri.parse(gitoriousUrl));
                startActivity(i);
                break;
            case R.id.githubBtn:
                final String githubUrl = getResources().getString(R.string.github_project_link);
                i.setData(Uri.parse(githubUrl));
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
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
        super.finish();
    }
}
