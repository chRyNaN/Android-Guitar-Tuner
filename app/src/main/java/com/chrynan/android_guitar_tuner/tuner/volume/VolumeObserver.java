package com.chrynan.android_guitar_tuner.tuner.volume;

import io.reactivex.Observable;

/**
 * An interface that provides a way to listen to volume state changes.
 */
public interface VolumeObserver {

    /**
     * Subscribes to the volume observer for volume states, either on an interval or changes to the
     * devices volume state.
     *
     * @return An {@link Observable<VolumeState>} providing the volume states as long as subscribed.
     */
    Observable<VolumeState> startListening();
}
