package com.chrynan.android_guitar_tuner.tuner.config;

/**
 * An interface providing access to audio recording and playback configuration details.
 */
public interface AudioConfig {

    /**
     * The sample rate that the Audio Recorder is using, in hertz. Note that this value is device
     * dependent.
     *
     * @return The sample rate of the audio recording.
     */
    int getSampleRate();

    /**
     * The size of the underlying waveform buffer array used by the Audio Recorder. This should be
     * larger than the value returned by {@link #getReadSize()}. This value should only be used to
     * create an instance of an Audio Recorder and when reading or performing operations on the
     * waveform buffer array, {@link #getReadSize()} should be used. Note that an implementation of
     * this method should return a valid value for the device.
     *
     * @return The size of the underlying waveform buffer array.
     */
    int getBufferSize();

    /**
     * The size of the array of data that should be read from the buffer. Use this value when
     * processing the buffer of waveform data.
     *
     * @return The size of values read from the underlying waveform buffer array.
     */
    int getReadSize();

    /**
     * The audio format of the buffer array (ex: float or short).
     *
     * @return The format of the buffer array.
     */
    int getAudioFormat();
}
