package com.chrynan.android_guitar_tuner.tuner.note;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A class that tests the accuracy of the {@link NoteName} class.
 */
public class NoteNameTest {

    @Test
    public void noteNameAShouldWork() {
        assertEquals(NoteName.A, NoteName.forName("A"));

        assertEquals("A", NoteName.A.getName());
    }

    @Test
    public void noteNameASharpShouldWork() {
        assertEquals(NoteName.A_SHARP, NoteName.forName("A♯"));

        assertEquals("A♯", NoteName.A_SHARP.getName());
    }

    @Test
    public void noteNameBShouldWork() {
        assertEquals(NoteName.B, NoteName.forName("B"));

        assertEquals("B", NoteName.B.getName());
    }

    @Test
    public void noteNameCShouldWork() {
        assertEquals(NoteName.C, NoteName.forName("C"));

        assertEquals("C", NoteName.C.getName());
    }

    @Test
    public void noteNameCSharpShouldWork() {
        assertEquals(NoteName.C_SHARP, NoteName.forName("C♯"));

        assertEquals("C♯", NoteName.C_SHARP.getName());
    }

    @Test
    public void noteNameDShouldWork() {
        assertEquals(NoteName.D, NoteName.forName("D"));

        assertEquals("D", NoteName.D.getName());
    }

    @Test
    public void noteNameDSharpShouldWork() {
        assertEquals(NoteName.D_SHARP, NoteName.forName("D♯"));

        assertEquals("D♯", NoteName.D_SHARP.getName());
    }

    @Test
    public void noteNameEShouldWork() {
        assertEquals(NoteName.E, NoteName.forName("E"));

        assertEquals("E", NoteName.E.getName());
    }

    @Test
    public void noteNameFShouldWork() {
        assertEquals(NoteName.F, NoteName.forName("F"));

        assertEquals("F", NoteName.F.getName());
    }

    @Test
    public void noteNameFSharpShouldWork() {
        assertEquals(NoteName.F_SHARP, NoteName.forName("F♯"));

        assertEquals("F♯", NoteName.F_SHARP.getName());
    }

    @Test
    public void noteNameGShouldWork() {
        assertEquals(NoteName.G, NoteName.forName("G"));

        assertEquals("G", NoteName.G.getName());
    }

    @Test
    public void noteNameGSharpShouldWork() {
        assertEquals(NoteName.G_SHARP, NoteName.forName("G♯"));

        assertEquals("G♯", NoteName.G_SHARP.getName());
    }

    @Test
    public void undefinedNoteNameShouldWork() {
        assertEquals(NoteName.UNDEFINED, NoteName.forName("Undefined"));

        assertEquals("Undefined", NoteName.UNDEFINED.getName());
    }

    @Test
    public void lowerCaseShouldWork() {
        assertEquals(NoteName.A, NoteName.forName("a"));

        assertNotEquals("a", NoteName.A.getName());
    }

    @Test
    public void hashTagShouldWork() {
        assertEquals(NoteName.A_SHARP, NoteName.forName("A#"));

        assertNotEquals("A#", NoteName.A_SHARP.getName());
    }
}
