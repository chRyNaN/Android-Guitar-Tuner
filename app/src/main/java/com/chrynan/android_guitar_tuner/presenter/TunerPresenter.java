package com.chrynan.android_guitar_tuner.presenter;

import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.ui.TuningState;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TunerPresenter implements Presenter {

    private final TunerView view;
    private final Tuner tuner;

    private final Map<String, Integer> notes = new HashMap<>();

    private Disposable disposable;

    private float angleInterval;

    private double lastFrequency;

    public TunerPresenter(final TunerView view, final Tuner tuner) {
        this.view = view;
        this.tuner = tuner;
    }

    @Override
    public void detachView() {
        // No Operation
    }

    public void setNotes(final String[] noteNames) {
        for (int i = 0; i < noteNames.length; i++) {
            notes.put(noteNames[i], i);
        }

        angleInterval = (float) Math.toRadians(360 / notes.size());
    }

    public void startListeningForNotes() {
        disposable = tuner.startListening()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(note -> {
                    lastFrequency = note.getFrequency();

                    int p = notes.get(note.getName());

                    float angle = angleInterval * p + angleInterval * (note.getPercentOffset() / 100);

                    // FIXME update this to use the correct TuningState constant if wanted
                    view.onShowNote(note.getName(), angle, TuningState.UNDEFINED);
                });
    }

    public void stopListeningForNotes() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void notePressed(final String noteName, final float x, final float y) {
        view.onPlayNote(noteName, lastFrequency, x, y);
    }
}
