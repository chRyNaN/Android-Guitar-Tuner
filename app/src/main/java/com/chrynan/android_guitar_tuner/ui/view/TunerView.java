package com.chrynan.android_guitar_tuner.ui.view;

import com.chrynan.android_guitar_tuner.Radian;
import com.chrynan.android_guitar_tuner.ui.TuningState;

/**
 * A View interface for a view implementation that reacts to the result of a tuner.
 */
public interface TunerView {

    void onShowNote(String noteName, @Radian float angleRadians, @TuningState int tuningState);

    void onPlayNote(String noteName, double frequency, float x, float y);
}
