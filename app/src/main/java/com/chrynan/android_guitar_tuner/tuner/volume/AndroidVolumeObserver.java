package com.chrynan.android_guitar_tuner.tuner.volume;

import android.media.AudioManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * An Android implementation of the {@link VolumeObserver} interface.
 */
public class AndroidVolumeObserver implements VolumeObserver {

    private static final long INITIAL_DELAY = 0;
    private static final long INTERVAL = 3000;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;

    private final Observable<VolumeState> observable;

    public AndroidVolumeObserver(final AudioManager audioManager) {
        observable = Observable.interval(INITIAL_DELAY, INTERVAL, UNIT)
                .map(tick -> VolumeState.forVolume(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)));
    }

    @Override
    public Observable<VolumeState> startListening() {
        return observable.share();
    }
}
