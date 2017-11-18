package com.chrynan.android_guitar_tuner.tuner.note;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A class that tests the accuracy of a {@link MutableNote}.
 */
public class MutableNoteTest {

    private static final String DEFAULT_NOTE_NAME = "A";
    private static final double DEFAULT_FREQUENCY = 110.0d;
    private static final float DEFAULT_PERCENT_OFFSET = 0.0f;

    private MutableNote defaultNote;

    @Before
    public void setup() {
        defaultNote = new MutableNote(DEFAULT_NOTE_NAME, DEFAULT_FREQUENCY, DEFAULT_PERCENT_OFFSET);
    }

    @Test
    public void sameNotesShouldBeEqual() {
        MutableNote note = new MutableNote(DEFAULT_NOTE_NAME, DEFAULT_FREQUENCY, DEFAULT_PERCENT_OFFSET);

        assertEquals(defaultNote, note);
    }

    @Test
    public void differentNamesShouldNotBeEqual() {
        MutableNote note = new MutableNote("B", DEFAULT_FREQUENCY, DEFAULT_PERCENT_OFFSET);

        assertNotEquals(defaultNote, note);
        assertNotEquals(defaultNote.getName(), note.getName());

        assertEquals(defaultNote.getFrequency(), note.getFrequency(), 0);
        assertEquals(defaultNote.getPercentOffset(), note.getPercentOffset(), 0);
    }

    @Test
    public void differentFrequenciesShouldNotBeEqual() {
        MutableNote note = new MutableNote(DEFAULT_NOTE_NAME, 123.5d, DEFAULT_PERCENT_OFFSET);

        assertNotEquals(defaultNote, note);
        assertNotEquals(defaultNote.getFrequency(), note.getFrequency());

        assertEquals(defaultNote.getName(), note.getName());
        assertEquals(defaultNote.getPercentOffset(), note.getPercentOffset(), 0);
    }

    @Test
    public void differentPercentOffsetsShouldNotBeEqual() {
        MutableNote note = new MutableNote(DEFAULT_NOTE_NAME, DEFAULT_FREQUENCY, 5f);

        assertNotEquals(defaultNote, note);
        assertNotEquals(defaultNote.getPercentOffset(), note.getPercentOffset(), 0);

        assertEquals(defaultNote.getName(), note.getName());
        assertEquals(defaultNote.getFrequency(), note.getFrequency(), 0);
    }

    @Test
    public void completelyDifferentNotesShouldNoteBeEqual() {
        MutableNote note = new MutableNote("B", 123.5d, 5f);

        assertNotEquals(defaultNote, note);
        assertNotEquals(defaultNote.getName(), note.getName());
        assertNotEquals(defaultNote.getFrequency(), note.getFrequency(), 0);
        assertNotEquals(defaultNote.getPercentOffset(), note.getPercentOffset(), 0);
    }

    @Test
    public void changingNameShouldWork() {
        MutableNote note = defaultNote;

        assertEquals(note.getName(), DEFAULT_NOTE_NAME);

        note.setName("B");

        assertEquals(note.getName(), "B");
    }

    @Test
    public void changingFrequencyShouldWork() {
        MutableNote note = defaultNote;

        assertEquals(note.getFrequency(), DEFAULT_FREQUENCY, 0);

        note.setFrequency(123.5d);

        assertEquals(note.getFrequency(), 123.5d, 0);
    }

    @Test
    public void changingPercentOffsetShouldWork() {
        MutableNote note = defaultNote;

        assertEquals(note.getPercentOffset(), DEFAULT_PERCENT_OFFSET, 0);

        note.setPercentOffset(5f);

        assertEquals(note.getPercentOffset(), 5f, 0);
    }
}
