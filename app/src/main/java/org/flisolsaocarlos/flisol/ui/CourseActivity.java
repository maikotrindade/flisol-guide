package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Course;

public class CourseActivity extends Activity {

    private ShareActionProvider mShareActionProvider;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(R.drawable.ic_launcher_white);

        final Bundle bundle = getIntent().getExtras();
        course = bundle.getParcelable("course");

        TextView courseTitle = (TextView) findViewById(R.id.title);
        courseTitle.setText(course.getTitle());

        TextView courseDescription = (TextView) findViewById(R.id.description);
        courseDescription.setText(course.getDescription());

        TextView courseCourser = (TextView) findViewById(R.id.lecturer);
        courseCourser.setText(course.getLecturer());

        TextView courseField = (TextView) findViewById(R.id.field);
        courseField.setText(course.getField());

        TextView courseScheduleBegin = (TextView) findViewById(R.id.schedule_begin);
        courseScheduleBegin.setText(course.getScheduleBegin());

        TextView courseScheduleEnd = (TextView) findViewById(R.id.schedule_end);
        courseScheduleEnd.setText(course.getScheduleEnd());

        TextView courseRoom = (TextView) findViewById(R.id.room);
        courseRoom.setText(course.getRoom());

        TextView courseVacancies = (TextView) findViewById(R.id.vacancies);
        courseVacancies.setText(course.getVacancies().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);

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
        if (course != null) {
            sharedIntent.setAction(Intent.ACTION_SEND);
            sharedIntent.putExtra(Intent.EXTRA_TEXT, course.getTitle() +
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

