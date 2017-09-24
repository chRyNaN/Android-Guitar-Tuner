package com.chrynan.android_guitar_tuner.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.ui.dialog.PermissionRationalDialogFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PermissionActivity extends AppCompatActivity implements PermissionRationalDialogFragment.DialogListener {

    public static Intent newIntent(final Context context) {
        return new Intent(context, PermissionActivity.class);
    }

    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.permissionButton)
    Button permissionButton;

    private final PermissionRationalDialogFragment permissionRationalDialogFragment = PermissionRationalDialogFragment.newInstance();

    private PermissionToken token;

    private boolean userDeniedPermission;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permission);

        ButterKnife.bind(this);
    }

    @Override
    public void onDialogProceed() {
        if (token != null) {
            token.continuePermissionRequest();
        }
    }

    @Override
    public void onDialogCanceled() {
        if (token != null) {
            token.cancelPermissionRequest();
        }
    }

    @Override
    public void onDialogDismissed() {
        if (token != null) {
            token.cancelPermissionRequest();
        }
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
        } else {
            // Open the permission dialog
            handlePermissions();
        }
    }

    private void handlePermissions() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // Go to the Guitar Tuner Activity
                        startActivity(GuitarTunerActivity.newIntent(PermissionActivity.this));
                        finish();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // Show the grant access message
                        titleTextView.setText(R.string.permission_grant_access_title);
                        descriptionTextView.setText(R.string.permission_grant_access_description);
                        permissionButton.setText(R.string.permission_settings_button);
                        userDeniedPermission = true;
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        // Show more information about the permission needed to the user
                        PermissionActivity.this.token = token;
                        permissionRationalDialogFragment.show(getSupportFragmentManager(), PermissionRationalDialogFragment.TAG);
                    }
                })
                .onSameThread()
                .check();
    }
}
