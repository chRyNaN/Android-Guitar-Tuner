package com.chrynan.android_guitar_tuner.tuner.config;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;

/**
 * An Android implementation of the {@link AudioConfig} interface.
 */
public class AndroidAudioConfig implements AudioConfig {

    private static final int AUDIO_SAMPLE_RATE = 44100;

    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? AudioFormat.ENCODING_PCM_FLOAT : AudioFormat.ENCODING_PCM_16BIT;
    private static final int AUDIO_RECORD_BUFFER_SIZE = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE,
            AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT);
    private static final int AUDIO_RECORD_READ_SIZE = AUDIO_RECORD_BUFFER_SIZE / 4;
    private static final int AUDIO_RECORD_AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;

    private static final int AUDIO_PLAYER_CHANNEL_CONFIG = AudioFormat.CHANNEL_OUT_DEFAULT;
    private static final int AUDIO_PLAYER_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_FLOAT;
    private static final int AUDIO_PLAYER_BUFFER_SIZE = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE,
            AUDIO_PLAYER_CHANNEL_CONFIG, AUDIO_PLAYER_AUDIO_FORMAT);
    private static final int AUDIO_PLAYER_READ_SIZE = AUDIO_RECORD_BUFFER_SIZE / 4;

    public AndroidAudioConfig() {
        // Default constructor
    }

    @Override
    public int getSampleRate() {
        return AUDIO_SAMPLE_RATE;
    }

    @Override
    public int getInputBufferSize() {
        return AUDIO_RECORD_BUFFER_SIZE;
    }

    @Override
    public int getOutputBufferSize() {
        return AUDIO_PLAYER_BUFFER_SIZE;
    }

    @Override
    public int getReadSize() {
        return AUDIO_RECORD_READ_SIZE;
    }

    @Override
    public int getWriteSize() {
        return AUDIO_PLAYER_READ_SIZE;
    }

    @Override
    public int getInputChannel() {
        return AUDIO_RECORD_CHANNEL_CONFIG;
    }

    @Override
    public int getOutputChannel() {
        return AUDIO_PLAYER_CHANNEL_CONFIG;
    }

    @Override
    public int getInputFormat() {
        return AUDIO_RECORD_AUDIO_FORMAT;
    }

    @Override
    public int getOutputFormat() {
        return AUDIO_PLAYER_AUDIO_FORMAT;
    }

    @Override
    public int getInputSource() {
        return AUDIO_RECORD_AUDIO_SOURCE;
    }
}
