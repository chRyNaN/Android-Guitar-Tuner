package com.chrynan.android_guitar_tuner.tuner.converter;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

/**
 * An implementation of the {@link FrequencyConverter} interface that converts a provided frequency
 * value into a perfect sinusoidal waveform array buffer.
 */
public class SineWaveFrequencyConverter implements FrequencyConverter {

    private static final double TWO_PI = 2 * Math.PI;

    private final int sampleRate;
    private final int initialWriteBufferSize;
    private final int outputFormatByteCount;

    public SineWaveFrequencyConverter(final AudioConfig audioConfig) {
        sampleRate = audioConfig.getSampleRate();
        initialWriteBufferSize = audioConfig.getWriteSize();
        outputFormatByteCount = audioConfig.getOutputFormatByteCount();
    }

    @Override
    public float[] convert(final double frequency) {
        // How many sine wave cycles of the frequency can occur for the sample rate
        // This can also be viewed as the wavelength (amount of indices for one complete cycle) within the resulting buffer
        double cyclesPerSampleRate = sampleRate / frequency;

        // How many sine wave cycles of the frequency can occur for the output buffer
        int cyclesPerBuffer = (int) (initialWriteBufferSize / cyclesPerSampleRate);

        // The largest, rounded amount of cycles that can fit in the initial buffer that is also divisible by the output format byte count
        int roundedCyclesPerBuffer = (cyclesPerBuffer / outputFormatByteCount) * outputFormatByteCount;

        // The final output buffer size
        int outputBufferSize = (int) (roundedCyclesPerBuffer * cyclesPerSampleRate);

        // The total rounding deviation that will occur over the course of the buffer
        double entireBufferDeviation = (roundedCyclesPerBuffer * cyclesPerSampleRate) % ((int) cyclesPerSampleRate);

        // The difference that should be applied to each buffer value to counter the total deviation and avoid "click" sounds
        double deviationDiff = entireBufferDeviation / outputBufferSize;

        float[] audioData = new float[outputBufferSize];

        for (int i = 0; i < audioData.length; i++) {
            // The y value of the sine wave at the specific buffer index
            // y(x) = sin(2 * PI * x / sampleRate / frequency) or
            // y(x) = sin(2 * PI * x * frequency / sampleRate)
            audioData[i] = (float) (Math.sin(TWO_PI * i / cyclesPerSampleRate) - deviationDiff);
        }

        return audioData;
    }
}
