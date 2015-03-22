package org.flisolsaocarlos.flisol.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.flisolsaocarlos.flisol.BuildConfig;
import org.flisolsaocarlos.flisol.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AboutAppActivity extends Activity implements View.OnClickListener {

    final static int MAX_NAME_LENGHT = 70;
    final static int MAX_SUBJECT_LENGHT = 150;
    final static int MAX_MESSAGE_LENGHT = 400;
    final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private TextView versionNameTxt;
    private Button licenseBtn, submitBtn;
    private EditText nameEdt, emailEdt, subjectEdt, messageEdt;

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

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        nameEdt = (EditText) findViewById(R.id.nameEdit);
        emailEdt = (EditText) findViewById(R.id.emailEdit);
        subjectEdt = (EditText) findViewById(R.id.subjectEdit);
        messageEdt = (EditText) findViewById(R.id.messageEdit);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.licenseBtn:
                Intent i = new Intent(Intent.ACTION_VIEW);
                final String licenseUrl = getResources().getString(R.string.license_link);
                i.setData(Uri.parse(licenseUrl));
                startActivity(i);
                break;
            case R.id.submitBtn:

                final String name = nameEdt.getText().toString();
                if ( (name.isEmpty()) && (name.length() < MAX_NAME_LENGHT) ) {
                    nameEdt.setError(getResources().getString(R.string.invalid_name));
                    break;
                }

                final String email = emailEdt.getText().toString();
                if (!isValidEmail(email)) {
                    emailEdt.setError(getResources().getString(R.string.invalid_email));
                    break;
                }

                final String subject = subjectEdt.getText().toString();
                if ( (subject.isEmpty()) && (subject.length() < MAX_SUBJECT_LENGHT) ) {
                    subjectEdt.setError(getResources().getString(R.string.invalid_subject));
                    break;
                }

                final String message = messageEdt.getText().toString();
                if ( (message.isEmpty()) && (message.length() < MAX_MESSAGE_LENGHT) ) {
                    messageEdt.setError(getResources().getString(R.string.invalid_message));
                    break;
                }

                submitEmail(email, name, subject, message);
                break;
        }
    }

    private void submitEmail(final String email, final String name,
                             final String subject, final String message) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        final String developerEmail = getResources().getString(R.string.developer_email);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{developerEmail});

        StringBuilder subjectBuilder = new StringBuilder();
        subjectBuilder.append("[FLISOL - ").append(subject).append(" ]");
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectBuilder.toString());

        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final Calendar cal = Calendar.getInstance();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Sent by ").append(name).append(" ")
                .append("<").append(email).append(">\n\n")
                .append(message).append("\n\n")
                .append("Date: ").append(dateFormat.format(cal.getTime()));
        intent.putExtra(Intent.EXTRA_TEXT, messageBuilder.toString());

        try {
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.send_email)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutAppActivity.this, getResources().getString(R.string.send_email_error), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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


