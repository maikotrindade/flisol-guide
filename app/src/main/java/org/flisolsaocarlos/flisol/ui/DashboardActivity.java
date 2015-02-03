package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.flisolsaocarlos.flisol.R;

public class DashboardActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        Button lectureBtn = (Button) findViewById(R.id.lectureBtn);
        lectureBtn.setOnClickListener(this);
        Button courseBtn = (Button) findViewById(R.id.courseBtn);
        courseBtn.setOnClickListener(this);
        Button installFestBtn = (Button) findViewById(R.id.installFestBtn);
        installFestBtn.setOnClickListener(this);
        Button locationBtn = (Button) findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(this);
        Button scheduleBtn = (Button) findViewById(R.id.scheduleBtn);
        scheduleBtn.setOnClickListener(this);
        Button supportersBtn = (Button) findViewById(R.id.supportersBtn);
        supportersBtn.setOnClickListener(this);
        Button aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lectureBtn:
                startSelectedActivity(LectureListActivity.class);
                break;
            case R.id.courseBtn:
                startSelectedActivity(CourseListActivity.class);
                break;
            case R.id.installFestBtn:
                startSelectedActivity(InstallFestActivity.class);
                break;
            case R.id.locationBtn:
                startSelectedActivity(LocationFragmentActivity.class);
                break;
            case R.id.supportersBtn:
                startSelectedActivity(SupportersListActivity.class);
                break;
            case R.id.scheduleBtn:
                startSelectedActivity(ScheduleActivity.class);
                break;
            case R.id.aboutBtn:
                startSelectedActivity(AboutActivity.class);
                break;
        }
    }

    private void startSelectedActivity(Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activityClass);
        startActivity(intent);
        overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }
}
