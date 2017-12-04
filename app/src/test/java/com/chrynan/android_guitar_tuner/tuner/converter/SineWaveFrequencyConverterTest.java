package com.chrynan.android_guitar_tuner.tuner.converter;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A class that tests the accuracy of a {@link SineWaveFrequencyConverter}.
 */
public class SineWaveFrequencyConverterTest {

    AudioConfig config = mock(AudioConfig.class);

    private SineWaveFrequencyConverter converter;

    @Before
    public void createSineWaveFrequencyConverter() {
        when(config.getSampleRate()).thenReturn(44100);
        when(config.getOutputFormatByteCount()).thenReturn(1);

        converter = new SineWaveFrequencyConverter(config);
    }

    @Test
    public void rangeShouldBeBetweenNegativeOneAndOne() {
        final double minFrequency = Double.MIN_VALUE;

        when(config.getWriteSize()).thenReturn((int) Math.ceil(minFrequency));

        float[] minResult = converter.convert(minFrequency);

        for (float f : minResult) {
            assertTrue(f < 1.0f);
            assertTrue(f > -1.0f);
        }

        final double mediumFrequency = Double.MAX_VALUE / 2;

        when(config.getWriteSize()).thenReturn((int) Math.ceil(mediumFrequency));

        float[] mediumResult = converter.convert(mediumFrequency);

        for (float f : mediumResult) {
            assertTrue(f < 1.0f);
            assertTrue(f > -1.0f);
        }

        final double maxFrequency = Double.MAX_VALUE;

        when(config.getWriteSize()).thenReturn((int) Math.ceil(maxFrequency));

        float[] maxResult = converter.convert(maxFrequency);

        for (float f : maxResult) {
            assertTrue(f < 1.0f);
            assertTrue(f > -1.0f);
        }
    }
}
