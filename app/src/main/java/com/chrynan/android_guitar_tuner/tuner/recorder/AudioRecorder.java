package com.chrynan.android_guitar_tuner.tuner.recorder;

/**
 * An interface for a class that records or reads audio data into a returned array buffer.
 */
public interface AudioRecorder {

    /**
     * Starts listening to audio data and prepares for a call to the {@link #readNext()} method.
     * Note that this method should be called before calling the {@link #readNext()} method to
     * prepare the recorder.
     */
    void startRecording();

    /**
     * Stops listening to audio data and cleans up any underlying resources used by the
     * implementation. Note that {@link #readNext()} should not be called after this method is
     * called without calling {@link #startRecording()} again.
     */
    void stopRecording();

    /**
     * Reads audio data into an array buffer and returns it. Note that the returned array may be
     * mutable depending on the implementation to provide better performance.
     *
     * @return A float array of audio data.
     */
    float[] readNext();
}
