package com.chrynan.android_guitar_tuner.tuner;

import com.chrynan.android_guitar_tuner.tuner.converter.FrequencyConverter;
import com.chrynan.android_guitar_tuner.tuner.player.AudioPlayer;

import io.reactivex.Completable;

/**
 * An implementation of the {@link PitchPlayer} interface.
 */
public class GuitarPitchPlayer implements PitchPlayer {

    private final AudioPlayer audioPlayer;
    private final FrequencyConverter frequencyConverter;

    public GuitarPitchPlayer(final AudioPlayer audioPlayer, final FrequencyConverter frequencyConverter) {
        this.audioPlayer = audioPlayer;
        this.frequencyConverter = frequencyConverter;
    }

    @Override
    public Completable startPlaying(double frequency) {

        return Completable.create(emitter -> {
            try {
                final float[] audioData = frequencyConverter.convert(frequency);

                audioPlayer.setBuffer(audioData);

                audioPlayer.play();
            } catch (Exception exception) {
                emitter.tryOnError(exception);
            }
        }).doOnEvent(event -> stopPlaying())
                .doOnDispose(this::stopPlaying);
    }

    private void stopPlaying() {
        audioPlayer.stop();
        audioPlayer.release();
    }
}
