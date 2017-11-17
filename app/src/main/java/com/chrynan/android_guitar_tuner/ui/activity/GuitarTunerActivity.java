package com.chrynan.android_guitar_tuner.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.ui.fragment.CircleGuitarTunerFragment;
import com.chrynan.android_guitar_tuner.ui.fragment.PitchPlayerFragment;
import com.chrynan.android_guitar_tuner.ui.transition.CircularRevealTransition;
import com.chrynan.android_guitar_tuner.ui.view.TunerPitchToggleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuitarTunerActivity extends AppCompatActivity implements TunerPitchToggleView,
        CircleGuitarTunerFragment.OnPlayNoteListener {

    public static Intent newIntent(final Context context) {
        return new Intent(context, GuitarTunerActivity.class);
    }

    private static final String SHOW_PITCH_PLAYER_KEY = "showPitchPlayer";
    private static final String NOTE_NAME_KEY = "noteName";
    private static final String FREQUENCY_KEY = "frequency";

    private static final int TRANSITION_DURATION = 500;
    private static final int TRANSITION_DELAY = 0;
    private static final Interpolator TRANSITION_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private final CircleGuitarTunerFragment circleGuitarTunerFragment = CircleGuitarTunerFragment.newInstance();

    private final CircularRevealTransition circularRevealTransition = new CircularRevealTransition()
            .setDuration(TRANSITION_DURATION)
            .setStartDelay(TRANSITION_DELAY)
            .setInterpolator(TRANSITION_INTERPOLATOR);

    private boolean showPitchPlayer;
    private String lastNoteName = "";
    private double lastFrequency = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guitar_tuner);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String noteName = "";
        double frequency = 0;

        if (savedInstanceState != null) {
            showPitchPlayer = savedInstanceState.getBoolean(SHOW_PITCH_PLAYER_KEY, false);
            noteName = savedInstanceState.getString(NOTE_NAME_KEY, "");
            frequency = savedInstanceState.getDouble(FREQUENCY_KEY);
        }

        if (showPitchPlayer) {
            showPitchPlayback(noteName, frequency, 0, 0, false);
        } else {
            showGuitarTuner();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guitar_tuner_activity_menu, menu);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showPitchPlayer);

            if (showPitchPlayer) {
                actionBar.setTitle(PitchPlayerFragment.TITLE);
                actionBar.setHomeAsUpIndicator(R.drawable.app_bar_cancel_icon);
            } else {
                actionBar.setTitle(CircleGuitarTunerFragment.TITLE);
            }
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Check if we still have the correct permissions
        // If we don't we have to close this Activity and open the Permission Activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            startActivity(PermissionActivity.newIntent(this));
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                showGuitarTuner();
                return true;
            case R.id.shareAction:
                shareApp();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(PitchPlayerFragment.TAG) != null) {
            showGuitarTuner();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save the user denied boolean field
        if (savedInstanceState != null) {
            savedInstanceState.putBoolean(SHOW_PITCH_PLAYER_KEY, showPitchPlayer);
            savedInstanceState.putString(NOTE_NAME_KEY, lastNoteName);
            savedInstanceState.putDouble(FREQUENCY_KEY, lastFrequency);
        }
    }

    @Override
    public void showGuitarTuner() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, circleGuitarTunerFragment).commit();

        updateNavBar(false);
    }

    @Override
    public void showPitchPlayback(final String noteName, final double frequency, final float x, final float y, final boolean animate) {
        lastNoteName = noteName;
        lastFrequency = frequency;

        PitchPlayerFragment fragment = PitchPlayerFragment.newInstance(noteName, frequency);

        if (animate) {
            circularRevealTransition.setCenter((int) x, (int) y);

            fragment.setEnterTransition(circularRevealTransition);
            fragment.setExitTransition(circularRevealTransition);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, PitchPlayerFragment.TAG).commit();

        updateNavBar(true);
    }

    @Override
    public void onPlayNote(final String noteName, final double frequency, final float x, final float y) {
        showPitchPlayback(noteName, frequency, x, y, true);
    }

    private void updateNavBar(final boolean showingPitchPlayer) {
        showPitchPlayer = showingPitchPlayer;

        if (toolbar != null) {
            TransitionManager.beginDelayedTransition(toolbar);
        }

        invalidateOptionsMenu();
    }

    private void shareApp() {
        Resources res = getResources();
        String desc = res.getString(R.string.share_app_description, res.getString(R.string.app_name), res.getString(R.string.share_app_link));
        Intent shareIntent = new Intent(Intent.ACTION_SEND)
                .setType(res.getString(R.string.share_app_intent_type))
                .putExtra(Intent.EXTRA_SUBJECT, res.getString(R.string.share_app_subject))
                .putExtra(Intent.EXTRA_TEXT, desc);
        startActivity(Intent.createChooser(shareIntent, res.getString(R.string.share_app_intent_title)));
    }
}
