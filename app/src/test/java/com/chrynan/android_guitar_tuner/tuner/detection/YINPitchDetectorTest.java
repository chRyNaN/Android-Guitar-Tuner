package com.chrynan.android_guitar_tuner.tuner.detection;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * A class that tests the accuracy of a {@link YINPitchDetector} class.
 */
public class YINPitchDetectorTest {

    private static final int SAMPLE_RATE = 44100;
    private static final int READ_SIZE = 10000;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private YINPitchDetector detector;

    @Before
    public void setupDetector() {
        // Mock the AudioConfig interface
        AudioConfig config = Mockito.mock(AudioConfig.class);

        Mockito.when(config.getSampleRate()).thenReturn(SAMPLE_RATE);
        Mockito.when(config.getReadSize()).thenReturn(READ_SIZE);

        detector = new YINPitchDetector(config);
    }

}
