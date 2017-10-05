package com.chrynan.android_guitar_tuner.tuner.note;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class that tests the accuracy of an {@link ArrayNoteFinder}.
 */
public class ArrayNoteFinderTest {

    private ArrayNoteFinder arrayNoteFinder;

    @Before
    public void createNoteFinder() {
        arrayNoteFinder = new ArrayNoteFinder();
    }

    @Test
    public void highNoteOutOfRangeShouldBeNoteF() {
        arrayNoteFinder.setFrequency(6000.00);

        assertEquals("F", arrayNoteFinder.getNoteName());
    }

    @Test
    public void highestNoteShouldBeNoteF() {
        arrayNoteFinder.setFrequency(5587.65);

        assertEquals("F", arrayNoteFinder.getNoteName());
    }

    @Test
    public void noteShouldBeNoteA() {
        arrayNoteFinder.setFrequency(440.000);

        assertEquals("A", arrayNoteFinder.getNoteName());
    }

    @Test
    public void lowestNoteShouldBeNoteC() {
        arrayNoteFinder.setFrequency(16.3516);

        assertEquals("C", arrayNoteFinder.getNoteName());
    }

    @Test
    public void lowNoteOutOfRangeShouldBeNoteC() {
        arrayNoteFinder.setFrequency(10.0000);

        assertEquals("C", arrayNoteFinder.getNoteName());
    }

    @Test
    public void percentDiffShouldBeZeroForExactFreq() {
        arrayNoteFinder.setFrequency(440.000);

        assertEquals(0f, arrayNoteFinder.getPercentageDifference(), 0f);
    }

    @Test
    public void percentDiffShouldBeNegativeForHigherNote() {
        arrayNoteFinder.setFrequency(441.00);

        assertTrue(arrayNoteFinder.getPercentageDifference() < 0);
    }

    @Test
    public void percentDiffShouldBePositiveForLowerNote() {
        arrayNoteFinder.setFrequency(439.00);

        assertTrue(arrayNoteFinder.getPercentageDifference() > 0);
    }

    @Test
    public void percentDiffShouldBeFifty() {
        arrayNoteFinder.setFrequency(453.082);

        assertEquals(50f, Math.abs(arrayNoteFinder.getPercentageDifference()), 0f);
    }

    @Test
    public void fullPercentDiffForHighNoteOutOfRange() {
        arrayNoteFinder.setFrequency(6000.00);

        assertEquals(100f, Math.abs(arrayNoteFinder.getPercentageDifference()), 0f);
    }

    @Test
    public void fullPercentDiffForLowNoteOutOfRange() {
        arrayNoteFinder.setFrequency(0);

        assertEquals(100f, Math.abs(arrayNoteFinder.getPercentageDifference()), 0f);
    }
}
