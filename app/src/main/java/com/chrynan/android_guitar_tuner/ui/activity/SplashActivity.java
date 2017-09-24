package com.chrynan.android_guitar_tuner.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // There is no layout to inflate for this screen.
        // The layout is the Activity Theme's background drawable attribute.
        // So, simply check if the user has granted permissions or not and send
        // them to the correct Activity.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            // Permission was already granted so go to the main Guitar Tuner Activity
            startActivity(GuitarTunerActivity.newIntent(this));
        } else {
            // Permission was not granted so go to the Permission Activity
            startActivity(PermissionActivity.newIntent(this));
        }

        // Finish this Activity so it doesn't appear in the back stack
        finish();
    }
}
