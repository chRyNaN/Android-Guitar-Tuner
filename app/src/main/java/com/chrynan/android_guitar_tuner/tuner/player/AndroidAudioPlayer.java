package com.chrynan.android_guitar_tuner.tuner.player;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

/**
 * An Android implementation of the {@link AudioPlayer} interface that works with the Android
 * {@link android.media.AudioTrack} object.
 */
public class AndroidAudioPlayer implements AudioPlayer {

    private static final int LOOP_COUNT_INFINITE = -1;

    private final AudioTrack audioTrack;

    private final int outputByteCount;

    public AndroidAudioPlayer(final AudioConfig audioConfig) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        AudioFormat audioFormat = new AudioFormat.Builder()
                .setChannelMask(audioConfig.getOutputChannel())
                .setEncoding(audioConfig.getOutputFormat())
                .setSampleRate(audioConfig.getSampleRate())
                .build();

        audioTrack = new AudioTrack(audioAttributes,
                audioFormat,
                audioConfig.getOutputBufferSize(),
                AudioTrack.MODE_STATIC,
                AudioManager.AUDIO_SESSION_ID_GENERATE);

        outputByteCount = audioConfig.getOutputFormatByteCount();
    }

    @Override
    public void play() {
        audioTrack.reloadStaticData();
        audioTrack.play();
    }

    @Override
    public void stop() {
        audioTrack.pause();
        audioTrack.flush();
        audioTrack.stop();
    }

    @Override
    public void release() {
        audioTrack.release();
    }

    @Override
    public void setBuffer(final float[] waveformBuffer) {
        audioTrack.write(waveformBuffer, 0, waveformBuffer.length, AudioTrack.WRITE_BLOCKING);
        audioTrack.setLoopPoints(0, waveformBuffer.length / outputByteCount, LOOP_COUNT_INFINITE);
    }
}
