package com.chrynan.android_guitar_tuner.ui.view;

/**
 * A View interface for a view implementation that reacts to the result of a tuner.
 */
public interface TunerView {

    void onShowNote(String noteName, float percentOffset);

    void onPlayNote(String noteName, double frequency, float x, float y);
}
