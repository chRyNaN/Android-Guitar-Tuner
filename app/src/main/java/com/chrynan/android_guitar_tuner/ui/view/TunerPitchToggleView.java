package com.chrynan.android_guitar_tuner.ui.view;

/**
 * A View interface for toggling between the default guitar tuner and pitch playback screens.
 */
public interface TunerPitchToggleView {

    void showGuitarTuner();

    void showPitchPlayback(String note, double frequency, float x, float y, boolean animate);
}
