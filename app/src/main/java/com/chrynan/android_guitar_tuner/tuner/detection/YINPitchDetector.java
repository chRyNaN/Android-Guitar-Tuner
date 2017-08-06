package com.chrynan.android_guitar_tuner.tuner.detection;

import com.chrynan.android_guitar_tuner.tuner.AudioConfig;

/**
 * A {@link PitchDetector} implementation that uses a YIN algorithm to determine the frequency of
 * the provided waveform data. The YIN algorithm is similar to the Auto-correlation Function used
 * for pitch detection but adds additional steps to better the accuracy of the results. Each step
 * lowers the error rate further. The following implementation was inspired by
 * <a href="https://github.com/JorenSix/TarsosDSP/blob/master/src/core/be/tarsos/dsp/pitch/Yin.javaz">TarsosDsp</a>
 * and
 * <a href="http://recherche.ircam.fr/equipes/pcm/cheveign/ps/2002_JASA_YIN_proof.pdf">this YIN paper</a>.
 * The six steps in the YIN algorithm are (according to the YIN paper):
 * <p>
 * <ol>
 * <li>Auto-correlation Method</li>
 * <li>Difference Function</li>
 * <li>Cumulative Mean Normalized Difference Function</li>
 * <li>Absolute Threshold</li>
 * <li>Parabolic Interpolation</li>
 * <li>Best Local Estimate</li>
 * </ol>
 * </p>
 */
public class YINPitchDetector implements PitchDetector {
    private final AudioConfig audioConfig;

    public YINPitchDetector(final AudioConfig audioConfig) {
        this.audioConfig = audioConfig;
    }

    @Override
    public double detect(short[] wave) {
        return 0;
    }
}
