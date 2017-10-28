package com.chrynan.android_guitar_tuner.tuner.converter;

/**
 * An interface for a class that converts a provided frequency into a buffer array of waveform audio
 * data.
 */
public interface FrequencyConverter {

    /**
     * Converts the provided frequency value into a float array representing the audio waveform
     * data. Note that it is possible that an implementation may return a mutable array that could
     * be changed after subsequent calls. This could be done for efficiency reasons (to avoid
     * constant array creation).
     *
     * @param frequency The provided frequency to convert.
     * @return The audio waveform buffer representing the provided frequency.
     */
    float[] convert(double frequency);
}
