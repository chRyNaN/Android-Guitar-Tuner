package com.chrynan.android_guitar_tuner.tuner.effect;

/**
 * An interface for a class that applies an effect to an audio buffer.
 */
public interface AudioEffect {

    /**
     * Performs the effect to the provided audio buffer and returns a new audio buffer representing
     * the result of the effect.
     *
     * @param audioWaveBuffer The audio buffer to perform the effect on.
     * @return A new audio buffer representing the provided audio buffer with the effect applied.
     */
    float[] execute(float[] audioWaveBuffer);
}
