package com.chrynan.android_guitar_tuner.tuner.note;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A class that tests the accuracy of a {@link MapFrequencyFinder}.
 */
public class MapFrequencyFinderTest {

    private MapFrequencyFinder finder;

    @Before
    public void setup() {
        finder = new MapFrequencyFinder();
    }

    @Test
    public void noteAShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.A);

        assertEquals(110.0, frequency, 0.0);
    }

    @Test
    public void noteASharpShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.A_SHARP);

        assertEquals(116.5, frequency, 0.0);
    }

    @Test
    public void noteBShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.B);

        assertEquals(123.5, frequency, 0.0);
    }

    @Test
    public void noteCShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.C);

        assertEquals(130.8, frequency, 0.0);
    }

    @Test
    public void noteCSharpShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.C_SHARP);

        assertEquals(138.6, frequency, 0.0);
    }

    @Test
    public void noteDShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.D);

        assertEquals(146.8, frequency, 0.0);
    }

    @Test
    public void noteDSharpShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.D_SHARP);

        assertEquals(155.6, frequency, 0.0);
    }

    @Test
    public void noteEShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.E);

        assertEquals(164.8, frequency, 0.0);
    }

    @Test
    public void noteFShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.F);

        assertEquals(174.6, frequency, 0.0);
    }

    @Test
    public void noteFSharpShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.F_SHARP);

        assertEquals(185.0, frequency, 0.0);
    }

    @Test
    public void noteGShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.G);

        assertEquals(196.0, frequency, 0.0);
    }

    @Test
    public void noteGSharpShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.G_SHARP);

        assertEquals(207.6, frequency, 0.0);
    }

    @Test
    public void undefinedNoteShouldBeCorrect() {
        double frequency = finder.getFrequency(NoteName.UNDEFINED);

        assertEquals(0, frequency, 0.0);
    }
}
