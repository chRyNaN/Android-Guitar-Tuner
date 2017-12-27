package com.chrynan.android_guitar_tuner.ui.view;

/**
 * A View interface for a view implementation that draws the current Note received from a
 * {@link com.chrynan.android_guitar_tuner.tuner.Tuner}.
 */
public interface TunerWidgetView {

    /**
     * Updates the view to display the provided information about a Note.
     *
     * @param noteName      The String to display that represents the current note.
     * @param frequency     The frequency of the current note.
     * @param percentOffset The percent offset used to illustrate the difference between the current
     *                      frequency and the closest supported frequency values.
     */
    void updateNote(String noteName, double frequency, float percentOffset);
}
