package com.chrynan.android_guitar_tuner.note;

import com.chrynan.android_guitar_tuner.tuner.note.NoteName;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A class that tests the accuracy of the
 * {@link com.chrynan.android_guitar_tuner.tuner.note.NoteName} class.
 */
public class NoteNameTest {

    @Test
    public void noteNameAShouldWork() {
        assertEquals(NoteName.A, NoteName.getFor("A"));

        assertEquals("A", NoteName.A.getName());
    }

    @Test
    public void noteNameASharpShouldWork() {
        assertEquals(NoteName.A_SHARP, NoteName.getFor("A♯"));

        assertEquals("A♯", NoteName.A_SHARP.getName());
    }

    @Test
    public void noteNameBShouldWork() {
        assertEquals(NoteName.B, NoteName.getFor("B"));

        assertEquals("B", NoteName.B.getName());
    }

    @Test
    public void noteNameCShouldWork() {
        assertEquals(NoteName.C, NoteName.getFor("C"));

        assertEquals("C", NoteName.C.getName());
    }

    @Test
    public void noteNameCSharpShouldWork() {
        assertEquals(NoteName.C_SHARP, NoteName.getFor("C♯"));

        assertEquals("C♯", NoteName.C_SHARP.getName());
    }

    @Test
    public void noteNameDShouldWork() {
        assertEquals(NoteName.D, NoteName.getFor("D"));

        assertEquals("D", NoteName.D.getName());
    }

    @Test
    public void noteNameDSharpShouldWork() {
        assertEquals(NoteName.D_SHARP, NoteName.getFor("D♯"));

        assertEquals("D♯", NoteName.D_SHARP.getName());
    }

    @Test
    public void noteNameEShouldWork() {
        assertEquals(NoteName.E, NoteName.getFor("E"));

        assertEquals("E", NoteName.E.getName());
    }

    @Test
    public void noteNameFShouldWork() {
        assertEquals(NoteName.F, NoteName.getFor("F"));

        assertEquals("F", NoteName.F.getName());
    }

    @Test
    public void noteNameFSharpShouldWork() {
        assertEquals(NoteName.F_SHARP, NoteName.getFor("F♯"));

        assertEquals("F♯", NoteName.F_SHARP.getName());
    }

    @Test
    public void noteNameGShouldWork() {
        assertEquals(NoteName.G, NoteName.getFor("G"));

        assertEquals("G", NoteName.G.getName());
    }

    @Test
    public void noteNameGSharpShouldWork() {
        assertEquals(NoteName.G_SHARP, NoteName.getFor("G♯"));

        assertEquals("G♯", NoteName.G_SHARP.getName());
    }

    @Test
    public void undefinedNoteNameShouldWork() {
        assertEquals(NoteName.UNDEFINED, NoteName.getFor("Undefined"));

        assertEquals("Undefined", NoteName.UNDEFINED.getName());
    }

    @Test
    public void lowerCaseShouldWork() {
        assertEquals(NoteName.A, NoteName.getFor("a"));

        assertNotEquals("a", NoteName.A.getName());
    }

    @Test
    public void hashTagShouldWork() {
        assertEquals(NoteName.A_SHARP, NoteName.getFor("A#"));

        assertNotEquals("A#", NoteName.A_SHARP.getName());
    }
}
