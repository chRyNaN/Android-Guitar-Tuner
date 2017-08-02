package com.chrynan.android_guitar_tuner.tuner.note;

/**
 * An interface for implementations that finds the closest note name and frequency difference
 * based on a provided frequency value.
 */
public interface NoteFinder {

    /**
     * Sets the frequency of the note. This method must be called before calling the other methods.
     * Failure to do so may result in unspecified behavior.
     *
     * @param frequency The frequency of the note.
     */
    void setFrequency(double frequency);

    /**
     * Retrieves a String representation of the closest note to the provided frequency.
     *
     * @return The String note name.
     */
    String getNoteName();

    /**
     * Retrieves the percent difference between the closest found note and the provided frequency.
     * It is an offset in percentage between the closest note and the second closest note. A
     * negative value will indicate it is a lower pitch than the closest found note and a positive
     * value will indicate it is a higher pitch than the closest found note.
     *
     * @return The percent offset from the closest note.
     */
    float getPercentageDifference();
}
