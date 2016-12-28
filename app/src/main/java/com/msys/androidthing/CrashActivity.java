package com.msys.androidthing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by Chokkar on 12/23/2016.
 */

public class CrashActivity extends AppCompatActivity {
    private static final String TAG = CrashActivity.class.getSimpleName();
    private CheckBox catchCrashCheckBox;
    private Button crashButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.notifyText));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        catchCrashCheckBox = (CheckBox) findViewById(R.id.catchCrashCheckBox);
        crashButton = (Button) findViewById(R.id.crashButton);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log that crash button was clicked. This version of Crash.log() will include the
                // message in the crash report as well as show the message in logcat.
                FirebaseCrash.logcat(Log.INFO, TAG, "Crash button clicked");

                // If catchCrashCheckBox is checked catch the exception and report is using
                // Crash.report(). Otherwise throw the exception and let Firebase Crash automatically
                // report the crash.
                if (catchCrashCheckBox.isChecked()) {
                    try {
                        throw new NullPointerException();
                    } catch (NullPointerException ex) {
                        // [START log_and_report]
                        FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                        FirebaseCrash.report(ex);
                        // [END log_and_report]
                    }
                } else {
                    throw new NullPointerException();
                }
            }
        });

        // Log that the Activity was created. This version of Crash.log() will include the message
        // in the crash report but will not be shown in logcat.
        // [START log_event]
        FirebaseCrash.log("Activity created");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
