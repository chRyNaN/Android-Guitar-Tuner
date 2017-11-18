package com.chrynan.android_guitar_tuner.tuner.volume;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A class that tests the accuracy of the {@link VolumeState} class.
 */
public class VolumeStateTest {

    @Test
    public void mutedVolumeShouldWork() {
        assertEquals(VolumeState.MUTED, VolumeState.forVolume(0));

        assertEquals(VolumeState.MUTED, VolumeState.forVolume(-1));

        assertNotEquals(VolumeState.MUTED, VolumeState.forVolume(1));
    }

    @Test
    public void unmutedVolumeShouldWork() {
        assertEquals(VolumeState.UNMUTED, VolumeState.forVolume(1));

        assertEquals(VolumeState.UNMUTED, VolumeState.forVolume(2));

        assertNotEquals(VolumeState.UNMUTED, VolumeState.forVolume(0));
    }
}
