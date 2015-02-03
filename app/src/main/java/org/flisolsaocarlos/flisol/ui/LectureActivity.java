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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        final Bundle bundle = getIntent().getExtras();
        lecture = bundle.getParcelable("lecture");

        TextView lectureTitle = (TextView) findViewById(R.id.title);
        lectureTitle.setText(lecture.getTitle());

        TextView lectureDescription = (TextView) findViewById(R.id.description);
        lectureDescription.setText(lecture.getDescription());

        TextView lectureLecturer = (TextView) findViewById(R.id.lecturer);
        lectureLecturer.setText(lecture.getLecturer());

        TextView lectureField = (TextView) findViewById(R.id.field);
        lectureField.setText(lecture.getField());

        TextView lectureScheduleBegin = (TextView) findViewById(R.id.schedule_begin);
        lectureScheduleBegin.setText(lecture.getScheduleBegin());

        TextView lectureScheduleEnd = (TextView) findViewById(R.id.schedule_end);
        lectureScheduleEnd.setText(lecture.getScheduleEnd());

        TextView lectureRoom = (TextView) findViewById(R.id.room);
        lectureRoom.setText(lecture.getRoom());

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
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
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
    protected void onDestroy() {
        super.onDestroy();

    }
}

