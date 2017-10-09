package com.chrynan.android_guitar_tuner.tuner.volume;

/**
 * An enum representing a device's volume state which is useful for audio playback.
 */
public enum VolumeState {

    MUTED,
    UNMUTED;

    /**
     * Retrieves a {@link VolumeState} for the provided volume.
     *
     * @param volume The volume.
     * @return A {@link VolumeState} representing the provided volume.
     */
    public static VolumeState forVolume(final int volume) {
        if (volume < 1) {
            return MUTED;
        }

        return UNMUTED;
    }
}
