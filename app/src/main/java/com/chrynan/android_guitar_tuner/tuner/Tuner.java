package com.chrynan.android_guitar_tuner.tuner;

/**
 * An interface for all tuner implementations.
 */
public interface Tuner {

    void start();

    void stop();

    void setTunerListener(TunerListener listener);

    void removeTunerListener();
}
