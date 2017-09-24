package com.chrynan.android_guitar_tuner.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.ui.dialog.PermissionRationalDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PermissionActivity extends AppCompatActivity implements PermissionRationalDialogFragment.DialogListener {

    public static Intent newIntent(final Context context) {
        return new Intent(context, PermissionActivity.class);
    }

    private static final String USER_DENIED_PERMISSION_KEY = "userDeniedPermission";
    private static final int RECORD_AUDIO_PERMISSION_REQUEST_CODE = 0;

    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.permissionButton)
    Button permissionButton;

    private final PermissionRationalDialogFragment permissionRationalDialogFragment = PermissionRationalDialogFragment.newInstance();

    private boolean userDeniedPermission;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permission);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            userDeniedPermission = savedInstanceState.getBoolean(USER_DENIED_PERMISSION_KEY, false);
        }

        // Show the correct text according to the userDeniedPermission field.
        // This helps to retain state across orientation changes.
        if (userDeniedPermission) {
            titleTextView.setText(R.string.permission_grant_access_title);
            descriptionTextView.setText(R.string.permission_grant_access_description);
            permissionButton.setText(R.string.permission_settings_button);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Check if we now have the right permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            // We have the right permissions, so move on to the Guitar Tuner Activity
            onPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        // Handle the results of the permissions request
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted();
            } else {
                onPermissionDenied();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save the user denied boolean field
        if (savedInstanceState != null) {
            savedInstanceState.putBoolean(USER_DENIED_PERMISSION_KEY, userDeniedPermission);
        }
    }

    @Override
    public void onDialogProceed() {
        requestPermissions();
    }

    @Override
    public void onDialogCanceled() {
        // Dialog canceled - no operation
    }

    @Override
    public void onDialogDismissed() {
        // Dialog dismissed - no operation
    }

    @OnClick(R.id.exitButton)
    void onExitClick() {
        // Close the app
        finish();
    }

    @OnClick(R.id.permissionButton)
    void onPermissionClick() {
        if (userDeniedPermission) {
            // Go to the Android Application Settings Activity
            Intent intent = new Intent()
                    .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.fromParts("package", getPackageName(), null))
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
            // Show more details about the permissions being requested
            onPermissionRationaleShouldBeShown();
        } else {
            // Open the permission dialog
            requestPermissions();
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_REQUEST_CODE);
    }

    private void onPermissionGranted() {
        // Go to the Guitar Tuner Activity
        startActivity(GuitarTunerActivity.newIntent(PermissionActivity.this));
        finish();
    }

    private void onPermissionDenied() {
        // Show the grant access message
        titleTextView.setText(R.string.permission_grant_access_title);
        descriptionTextView.setText(R.string.permission_grant_access_description);
        permissionButton.setText(R.string.permission_settings_button);
        userDeniedPermission = true;
    }

    private void onPermissionRationaleShouldBeShown() {
        // Show more information about the permission needed to the user
        permissionRationalDialogFragment.show(getSupportFragmentManager(), PermissionRationalDialogFragment.TAG);
    }
}
