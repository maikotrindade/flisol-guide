package org.flisolsaocarlos.flisolapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.flisolsaocarlos.flisolapp.R;

public class DashboardActivity extends Activity implements View.OnClickListener {


    private Button lectureBtn, courseBtn, installFestBtn, locationBtn,
            agendaBtn, supportersBtn, aboutBtn;

    private ImageButton facebookBtn, twitterBtn, googlePlusBtn, softwareLivreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        lectureBtn = (Button) findViewById(R.id.lectureBtn);
        lectureBtn.setOnClickListener(this);

        courseBtn = (Button) findViewById(R.id.courseBtn);
        courseBtn.setOnClickListener(this);

        installFestBtn = (Button) findViewById(R.id.installFestBtn);
        installFestBtn.setOnClickListener(this);

        locationBtn = (Button) findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(this);

        agendaBtn = (Button) findViewById(R.id.agendaBtn);
        agendaBtn.setOnClickListener(this);

        supportersBtn = (Button) findViewById(R.id.supportersBtn);
        supportersBtn.setOnClickListener(this);

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);

        facebookBtn = (ImageButton) findViewById(R.id.facebookBtn);
        facebookBtn.setOnClickListener(this);

        twitterBtn = (ImageButton) findViewById(R.id.twitterBtn);
        twitterBtn.setOnClickListener(this);

        googlePlusBtn = (ImageButton) findViewById(R.id.googlePlusBtn);
        googlePlusBtn.setOnClickListener(this);

        softwareLivreBtn = (ImageButton) findViewById(R.id.softwareLivreBtn);
        softwareLivreBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lectureBtn:
                startActivity(LectureListActivity.class);
                break;
            case R.id.courseBtn:
                startActivity(CourseListActivity.class);
                break;
            case R.id.installFestBtn:
                startActivity(InstallFestActivity.class);
                break;
            case R.id.locationBtn:
                startActivity(LocationFragmentActivity.class);
                break;
            case R.id.supportersBtn:
                startActivity(SupportersListActivity.class);
                break;
            case R.id.agendaBtn:
                startActivity(AgendaActivity.class);
                break;
            case R.id.aboutBtn:
                startActivity(AboutActivity.class);
                break;
            case R.id.facebookBtn:
                final String urlFacebook = getResources().getString(R.string.facebook_url);
                startInternetIntent(urlFacebook);
                break;
            case R.id.twitterBtn:
                final String urlTwitter = getResources().getString(R.string.twitter_url);
                startInternetIntent(urlTwitter);
                break;
            case R.id.googlePlusBtn:
                final String urlGooglePlus = getResources().getString(R.string.google_plus_url);
                startInternetIntent(urlGooglePlus);
                break;
            case R.id.softwareLivreBtn:
                final String urlSoftwareLivre = getResources().getString(R.string.software_livre_url);
                startInternetIntent(urlSoftwareLivre);
                break;
        }
    }

    private void startActivity(Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activityClass);
        startActivity(intent);
        overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }

    private void startInternetIntent(String url) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
