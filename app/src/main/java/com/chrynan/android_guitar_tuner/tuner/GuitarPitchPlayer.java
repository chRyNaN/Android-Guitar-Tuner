package com.chrynan.android_guitar_tuner.tuner;

import com.chrynan.android_guitar_tuner.tuner.converter.FrequencyConverter;
import com.chrynan.android_guitar_tuner.tuner.effect.AudioEffect;
import com.chrynan.android_guitar_tuner.tuner.player.AudioPlayer;

import io.reactivex.Completable;

/**
 * An implementation of the {@link PitchPlayer} interface.
 */
public class GuitarPitchPlayer implements PitchPlayer {

    private final AudioPlayer audioPlayer;
    private final FrequencyConverter frequencyConverter;
    private final AudioEffect guitarAudioEffect;

    public GuitarPitchPlayer(final AudioPlayer audioPlayer, final FrequencyConverter frequencyConverter, final AudioEffect guitarAudioEffect) {
        this.audioPlayer = audioPlayer;
        this.frequencyConverter = frequencyConverter;
        this.guitarAudioEffect = guitarAudioEffect;
    }

    @Override
    public Completable startPlaying(double frequency) {

        return Completable.create(emitter -> {
            try {
                final float[] audioData = frequencyConverter.convert(frequency);

                final float[] guitarAudioData = guitarAudioEffect.execute(audioData);

                audioPlayer.setBuffer(guitarAudioData);

                audioPlayer.play();
            } catch (Exception exception) {
                emitter.tryOnError(exception);
            }
        }).doOnEvent(event -> audioPlayer.stop())
                .doOnDispose(this::stopPlayingAndRelease);
    }

    private void stopPlayingAndRelease() {
        audioPlayer.stop();
        audioPlayer.release();
    }
}
