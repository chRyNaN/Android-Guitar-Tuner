package com.chrynan.android_guitar_tuner.tuner.recorder;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;
import com.chrynan.android_guitar_tuner.tuner.converter.Converter;

/**
 * An Android implementation of the {@link AudioRecorder} interface that works with the Android
 * {@link android.media.AudioRecord} object.
 */
public class AndroidAudioRecorder implements AudioRecorder {

    private static final int AUDIO_RECORD_AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;
    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    private final Converter converter;
    private final AudioRecord audioRecorder;
    private final int readSize;
    private final short[] buffer;
    private final float[] floatBuffer;

    public AndroidAudioRecorder(final AudioConfig audioConfig, final Converter converter) {
        this.converter = converter;
        this.audioRecorder = new AudioRecord(AUDIO_RECORD_AUDIO_SOURCE, audioConfig.getSampleRate(),
                AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT, audioConfig.getBufferSize());
        this.readSize = audioConfig.getReadSize();
        this.buffer = new short[readSize];
        this.floatBuffer = new float[readSize];
    }

    @Override
    public void startRecording() {
        audioRecorder.startRecording();
    }

    @Override
    public void stopRecording() {
        audioRecorder.stop();
    }

    @Override
    public float[] readNext() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioRecorder.read(floatBuffer, 0, readSize, AudioRecord.READ_BLOCKING);
        } else {
            audioRecorder.read(buffer, 0, readSize);

            converter.convert(buffer, floatBuffer);
        }

        return floatBuffer;
    }
}
