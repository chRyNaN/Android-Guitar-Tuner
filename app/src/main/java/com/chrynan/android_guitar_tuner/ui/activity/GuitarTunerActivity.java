package com.chrynan.android_guitar_tuner.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.ui.dialog.PermissionRationalDialogFragment;
import com.chrynan.android_guitar_tuner.ui.fragment.CircleGuitarTunerFragment;
import com.chrynan.android_guitar_tuner.ui.fragment.PitchPlayerFragment;
import com.chrynan.android_guitar_tuner.ui.transition.CircularRevealTransition;
import com.chrynan.android_guitar_tuner.ui.view.TunerPitchToggleView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuitarTunerActivity extends AppCompatActivity implements TunerPitchToggleView,
        CircleGuitarTunerFragment.OnPlayNoteListener,
        PermissionRationalDialogFragment.DialogListener {

    private static final int TRANSITION_DURATION = 500;
    private static final int TRANSITION_DELAY = 0;
    private static final Interpolator TRANSITION_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private final CircleGuitarTunerFragment circleGuitarTunerFragment = CircleGuitarTunerFragment.newInstance();
    private final PermissionRationalDialogFragment permissionRationalDialogFragment = PermissionRationalDialogFragment.newInstance();

    private final CircularRevealTransition circularRevealTransition = new CircularRevealTransition()
            .setDuration(TRANSITION_DURATION)
            .setStartDelay(TRANSITION_DELAY)
            .setInterpolator(TRANSITION_INTERPOLATOR);

    private boolean showBack;

    private PermissionToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guitar_tuner);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        handlePermissions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBack);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                showGuitarTuner();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showGuitarTuner() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, circleGuitarTunerFragment).commit();

        updateNavBar(false);
    }

    @Override
    public void showPitchPlayback(final String noteName, final double frequency, final float x, final float y) {
        PitchPlayerFragment fragment = PitchPlayerFragment.newInstance(noteName, frequency);

        circularRevealTransition.setCenter((int) x, (int) y);

        fragment.setEnterTransition(circularRevealTransition);
        fragment.setExitTransition(circularRevealTransition);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

        updateNavBar(true);
    }

    @Override
    public void onPlayNote(final String noteName, final double frequency, final float x, final float y) {
        showPitchPlayback(noteName, frequency, x, y);
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

        finish();
    }

    @Override
    public void onDialogDismissed() {
        if (token != null) {
            token.cancelPermissionRequest();
        }

        finish();
    }

    private void handlePermissions() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, circleGuitarTunerFragment).commit();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        GuitarTunerActivity.this.finish();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        GuitarTunerActivity.this.token = token;
                        permissionRationalDialogFragment.show(getSupportFragmentManager(), PermissionRationalDialogFragment.TAG);
                    }
                })
                .check();
    }

    private void updateNavBar(final boolean showBackButton) {
        showBack = showBackButton;

        invalidateOptionsMenu();
    }
}
