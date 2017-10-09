package com.chrynan.android_guitar_tuner.presenter;

import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.ui.view.PitchView;

public class PitchPresenter implements Presenter {

    private final PitchView view;
    @ApplicationContext
    private final Context applicationContext;

    public PitchPresenter(final PitchView view, @ApplicationContext final Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;
    }

    @Override
    public void detachView() {
        // No Operation
    }

    public void startPlayingNote(final double noteFrequency) {
        // TODO
    }

    public void stopPlayingNote() {
        // TODO
    }

    public void startListeningToVolumeChanges() {
        // TODO
    }

    public void stopListeningToVolumeChanges() {
        // TODO
    }
}
