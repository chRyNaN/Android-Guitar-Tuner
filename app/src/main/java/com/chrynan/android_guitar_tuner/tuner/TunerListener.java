package com.chrynan.android_guitar_tuner.tuner;

/**
 * A listener interface for anything interested in updates from a {@link Tuner}.
 */
public interface TunerListener {

    void onNote(String noteName, double frequency, float percentOffset);
}
