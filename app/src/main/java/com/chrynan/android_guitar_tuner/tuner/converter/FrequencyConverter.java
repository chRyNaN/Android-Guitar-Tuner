package com.chrynan.android_guitar_tuner.tuner.converter;

/**
 * An interface for a class that converts a provided frequency into a buffer array of waveform audio
 * data.
 */
public interface FrequencyConverter {

    /**
     * Converts the provided frequency value into a float array representing the audio waveform
     * data with values in the range [-1, 1].
     *
     * @param frequency The provided frequency to convert.
     * @return The audio waveform buffer representing the provided frequency.
     */
    float[] convert(double frequency);
}
