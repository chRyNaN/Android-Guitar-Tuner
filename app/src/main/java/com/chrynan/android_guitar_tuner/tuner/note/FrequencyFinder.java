package com.chrynan.android_guitar_tuner.tuner.note;

/**
 * An interface providing a way to obtain a frequency from a given note name.
 */
public interface FrequencyFinder {

    /**
     * Retrieves a frequency for the provided note name.
     *
     * @param name The name of the note whose frequency is being retrieved.
     * @return The frequency representing the provided note name.
     */
    double getFrequency(NoteName name);
}
