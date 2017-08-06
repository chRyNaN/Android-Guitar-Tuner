package com.chrynan.android_guitar_tuner.tuner;

/**
 * An interface providing access to audio recording and playback configuration details.
 */
public interface AudioConfig {

    int getSampleRate();

    int getBufferSize();

    int getReadSize();
}
