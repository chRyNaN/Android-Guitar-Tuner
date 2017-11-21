package com.chrynan.android_guitar_tuner.tuner.effect;

/**
 * An implementation of the {@link AudioEffect} interface that transforms an audio buffer to mimic
 * the sound of a guitar. The original frequency of the provided audio buffer should be preserved
 * while adding the effect of it being played by a guitar.
 */
public class GuitarAudioEffect implements AudioEffect {

    @Override
    public float[] execute(float[] audioWaveBuffer) {
        return audioWaveBuffer;
    }
}
