package com.chrynan.android_guitar_tuner.tuner.converter;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

/**
 * An implementation of the {@link FrequencyConverter} interface that converts a provided frequency
 * value into a perfect sinusoidal waveform array buffer.
 */
public class SineWaveFrequencyConverter implements FrequencyConverter {

    private static final double TWO_PI = 2 * Math.PI;

    private final float[] audioData;
    private final int sampleRate;

    public SineWaveFrequencyConverter(final AudioConfig audioConfig) {
        audioData = new float[audioConfig.getOutputBufferSize()];
        sampleRate = audioConfig.getSampleRate();
    }

    @Override
    public float[] convert(final double frequency) {
        for (int i = 0; i < audioData.length; i++) {
            audioData[i] = (float) Math.sin(TWO_PI * i / (sampleRate / frequency));
        }

        return audioData;
    }
}
