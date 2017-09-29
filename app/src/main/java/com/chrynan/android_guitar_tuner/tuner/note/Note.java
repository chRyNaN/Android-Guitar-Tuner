package com.chrynan.android_guitar_tuner.tuner.note;

/**
 * An interface representing a musical note as a result of a pitch detection algorithm for
 * subscribers of a {@link com.chrynan.android_guitar_tuner.tuner.Tuner}.
 */
public interface Note {

    /**
     * Retrieves the name of the note.
     *
     * @return A String representing the name of the note.
     */
    String getName();

    /**
     * Retrieves the frequency value of the closest found note.
     *
     * @return A double value representing the frequency of the closest note.
     */
    double getFrequency();

    /**
     * Retrieves the percent offset from the closest found note and the actual found frequency.
     *
     * @return A float value representing the percent offset from the note.
     */
    float getPercentOffset();
}