package com.chrynan.android_guitar_tuner.tuner;

import android.media.AudioFormat;
import android.media.AudioRecord;

/**
 * An Android implementation of the {@link AudioConfig} interface.
 */
public class AndroidAudioConfig implements AudioConfig {
    private static final int AUDIO_RECORD_SAMPLE_RATE = 44100;
    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private static final int AUDIO_RECORD_BUFFER_SIZE = AudioRecord.getMinBufferSize(AUDIO_RECORD_SAMPLE_RATE,
            AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT);
    private static final int AUDIO_RECORD_READ_SIZE = AUDIO_RECORD_BUFFER_SIZE / 4;

    public AndroidAudioConfig() {
        // Default constructor
    }

    @Override
    public int getSampleRate() {
        return AUDIO_RECORD_SAMPLE_RATE;
    }

    @Override
    public int getBufferSize() {
        return AUDIO_RECORD_BUFFER_SIZE;
    }

    @Override
    public int getReadSize() {
        return AUDIO_RECORD_READ_SIZE;
    }
}
