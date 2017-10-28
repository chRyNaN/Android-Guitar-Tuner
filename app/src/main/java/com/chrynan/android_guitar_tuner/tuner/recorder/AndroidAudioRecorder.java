package com.chrynan.android_guitar_tuner.tuner.recorder;

import android.media.AudioRecord;
import android.os.Build;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;
import com.chrynan.android_guitar_tuner.tuner.converter.Converter;

/**
 * An Android implementation of the {@link AudioRecorder} interface that works with the Android
 * {@link android.media.AudioRecord} object.
 */
public class AndroidAudioRecorder implements AudioRecorder {

    private final Converter converter;
    private final AudioRecord audioRecorder;
    private final int readSize;
    private final short[] buffer;
    private final float[] floatBuffer;

    public AndroidAudioRecorder(final AudioConfig audioConfig, final Converter converter) {
        this.converter = converter;
        this.audioRecorder = new AudioRecord(audioConfig.getInputSource(), audioConfig.getSampleRate(),
                audioConfig.getInputChannel(), audioConfig.getInputFormat(), audioConfig.getInputBufferSize());
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
