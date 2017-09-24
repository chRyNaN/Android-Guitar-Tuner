package com.chrynan.android_guitar_tuner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.transition.TransitionManager;
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

    private boolean showBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guitar_tuner);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        showGuitarTuner();
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
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(PitchPlayerFragment.TAG) != null) {
            showGuitarTuner();
        } else {
            super.onBackPressed();
        }
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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, PitchPlayerFragment.TAG).commit();

        updateNavBar(true);
    }

    @Override
    public void onPlayNote(final String noteName, final double frequency, final float x, final float y) {
        showPitchPlayback(noteName, frequency, x, y);
    }

    private void updateNavBar(final boolean showBackButton) {
        showBack = showBackButton;

        if (toolbar != null) {
            TransitionManager.beginDelayedTransition(toolbar);
        }

        invalidateOptionsMenu();
    }
}
