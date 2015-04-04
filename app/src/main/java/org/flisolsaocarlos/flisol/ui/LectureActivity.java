package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Lecture;

public class LectureActivity extends Activity {

    private ShareActionProvider mShareActionProvider;
    private Lecture lecture;
    private TextView lectureTitleTxt, lectureDescriptionTxt, lectureLecturerTxt, lectureFieldTxt,
            lectureScheduleBeginTxt, lectureScheduleEndTxt, hostingPlaceTxt, lectureRoomTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        final Bundle bundle = getIntent().getExtras();
        lecture = bundle.getParcelable("lecture");
        final String hostingPlace = bundle.getString("hostingPlace");

        lectureTitleTxt = (TextView) findViewById(R.id.title);
        lectureTitleTxt.setText(lecture.getTitle());

        lectureDescriptionTxt = (TextView) findViewById(R.id.description);
        lectureDescriptionTxt.setText(lecture.getDescription());

        lectureLecturerTxt = (TextView) findViewById(R.id.lecturer);
        lectureLecturerTxt.setText(lecture.getLecturer());

        lectureFieldTxt = (TextView) findViewById(R.id.field);
        lectureFieldTxt.setText(lecture.getField());

        lectureScheduleBeginTxt = (TextView) findViewById(R.id.schedule_begin);
        lectureScheduleBeginTxt.setText(lecture.getScheduleBegin());

        lectureScheduleEndTxt = (TextView) findViewById(R.id.schedule_end);
        lectureScheduleEndTxt.setText(lecture.getScheduleEnd());

        hostingPlaceTxt = (TextView) findViewById(R.id.hosting_place);
        hostingPlaceTxt.setText(hostingPlace);

        lectureRoomTxt = (TextView) findViewById(R.id.room);
        lectureRoomTxt.setText(lecture.getRoom());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lecture, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        mShareActionProvider.setShareIntent(getDefaultSharedIntent());

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    private Intent getDefaultSharedIntent() {
        Intent sharedIntent = new Intent();
        if (lecture != null) {
            sharedIntent.setAction(Intent.ACTION_SEND);
            sharedIntent.putExtra(Intent.EXTRA_TEXT, lecture.getTitle() +
                    " @FlisolSaoCarlos #Flisol2015");
            sharedIntent.setType("text/plain");
            setShareIntent(sharedIntent);
        }
        return sharedIntent;
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

