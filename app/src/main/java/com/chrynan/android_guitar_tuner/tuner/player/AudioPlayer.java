package com.chrynan.android_guitar_tuner.tuner.player;

/**
 * An interface for a class that plays the provided audio waveform data.
 */
public interface AudioPlayer {

    /**
     * Starts playing the buffer provided in the {@link #setBuffer(float[])} method to the audio output.
     */
    void play();

    /**
     * Stops playing to the audio output the buffer provided in the {@link #setBuffer(float[])} method.
     */
    void stop();

    /**
     * Releases all the internal resources held by this AudioPlayer. This should be called when the
     * AudioPlayer is no longer needed and after a call to {@link #stop()}. After this method is
     * called, a new AudioPlayer will have to be created to play again.
     */
    void release();

    /**
     * Sets the audio waveform data that will be played to the audio output.
     *
     * @param waveformBuffer The waveform data to be played.
     */
    void setBuffer(float waveformBuffer[]);
}
