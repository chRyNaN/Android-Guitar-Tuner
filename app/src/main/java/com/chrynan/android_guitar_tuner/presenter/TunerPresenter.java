package com.chrynan.android_guitar_tuner.presenter;

import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.tuner.TunerListener;
import com.chrynan.android_guitar_tuner.ui.TuningState;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;

import java.util.HashMap;
import java.util.Map;

public class TunerPresenter implements Presenter, TunerListener {

    private final TunerView view;
    @ApplicationContext
    private final Context applicationContext;
    private final Tuner tuner;

    private final Map<String, Integer> notes = new HashMap<>();

    private float angleInterval;

    private double lastFrequency;

    public TunerPresenter(final TunerView view, @ApplicationContext final Context applicationContext, final Tuner tuner) {
        this.view = view;
        this.applicationContext = applicationContext;
        this.tuner = tuner;

        tuner.setTunerListener(this);
    }

    @Override
    public void detachView() {
        // No Operation
    }

    @Override
    public void onNote(final String noteName, final double frequency, final float percentageOffset) {
        this.lastFrequency = frequency;

        int p = notes.get(noteName);

        float angle = angleInterval * p + angleInterval * (percentageOffset / 100);

        // FIXME update this to use the correct TuningState constant if wanted
        view.onShowNote(noteName, angle, TuningState.UNDEFINED);
    }

    public void setNotes(final String[] noteNames) {
        for (int i = 0; i < noteNames.length; i++) {
            notes.put(noteNames[i], i);
        }

        angleInterval = (float) Math.toRadians(360 / notes.size());
    }

    public void startListeningForNotes() {
        tuner.start();
    }

    public void stopListeningForNotes() {
        tuner.stop();
    }

    public void notePressed(final String noteName, final float x, final float y) {
        view.onPlayNote(noteName, lastFrequency, x, y);
    }
}
