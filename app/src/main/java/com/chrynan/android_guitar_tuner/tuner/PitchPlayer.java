package com.chrynan.android_guitar_tuner.tuner;

import io.reactivex.Completable;

/**
 * An interface that plays a provided frequency.
 */
public interface PitchPlayer {

    /**
     * Starts playing the provided frequency and returns a {@link Completable} that performs the
     * playing of the frequency.
     *
     * @param frequency The frequency to be played.
     * @return A {@link Completable} running the playing operation until unsubscribed.
     */
    Completable startPlaying(double frequency);
}
