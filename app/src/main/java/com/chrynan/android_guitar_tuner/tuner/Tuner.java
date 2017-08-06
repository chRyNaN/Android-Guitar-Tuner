package com.chrynan.android_guitar_tuner.tuner;

/**
 * An interface for all tuner implementations.
 */
public interface Tuner {

    void start();

    void stop();

    void setListener(Listener listener);

    void removeListener();

    /**
     * A listener interface for anything interested in updates from a {@link Tuner}.
     */
    interface Listener {

        void onNote(String noteName, double frequency, float percentOffset);
    }
}
