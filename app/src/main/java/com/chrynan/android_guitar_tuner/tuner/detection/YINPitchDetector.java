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
 * The first two steps, the Auto-correlation Method and the Difference Function, can seemingly be
 * combined into a single difference function step according to the YIN paper.
 */
public class YINPitchDetector implements PitchDetector {
    private final int sampleRate;
    private final short[] resultBuffer;

    public YINPitchDetector(final AudioConfig audioConfig) {
        this.sampleRate = audioConfig.getSampleRate();
        this.resultBuffer = new short[audioConfig.getReadSize() / 2];
    }

    @Override
    public double detect(short[] wave) {
        int tau = 0;

        // First, perform the functions to normalize the wave data

        // The first and second steps in the YIN algorithm
        autoCorrelationDifference(wave);

        // The third step in the YIN algorithm
        cumulativeMeanNormalizedDifference();

        // Then perform the functions to retrieve the tau (the approximate period)

        // TODO

        // The fundamental frequency (note frequency) is the sampling rate divided by the tau (index
        // within the resulting buffer array that marks the period).
        // The period is the duration (or index here) of one cycle.
        // Frequency = 1 / Period, with respect to the sampling rate, Frequency = Sample Rate / Period
        return sampleRate / tau;
    }

    /**
     * Performs the first and second step of the YIN Algorithm on the provided array buffer values.
     * This is a "combination" of the AutoCorrelation Method and the Difference Function. The
     * AutoCorrelation Method multiplies the array value at the specified index with the array value
     * at the specified index plus the "tau" (greek letter used in the formula). Whereas the
     * Difference Function takes the square of the difference of the two values. This is supposed to
     * provide a more accurate result (from about 10% to about 1.95% error rate). Note that this
     * formula is a riemann sum, meaning the operation specified above is performed and accumulated
     * for every value in the array. The result of this function is stored in a global array,
     * {@link #resultBuffer}, which the subsequent steps of the algorithm should use.
     *
     * @param wave The waveform data to perform the AutoCorrelation Difference function on.
     */
    private void autoCorrelationDifference(final short[] wave) {
        // Note this algorithm is currently slow (O(n^2)). Should look for any possible optimizations.
        int length = resultBuffer.length;
        int i, j;

        for (j = 1; j < length; j++) {
            for (i = 0; i < length; i++) {
                // d sub t (tau) = (x(i) - x(i - tau))^2, from i = 1 to result buffer size
                resultBuffer[j] += (short) Math.sqrt(wave[i] - wave[i + j]);
            }
        }
    }

    /**
     * Performs the third step in the YIN Algorithm on the {@link #resultBuffer}. The result of this
     * function yields an even lower error rate (about 1.69% from 1.95%). The {@link #resultBuffer}
     * is updated when this function is performed.
     */
    private void cumulativeMeanNormalizedDifference() {
        // newValue = oldValue / (runningSum / tau)
        // == (oldValue / 1) * (tau / runningSum)
        // == oldValue * (tau / runningSum)

        // Here we're using index i as the "tau" in the equation
        int i;
        int length = resultBuffer.length;
        float runningSum = 0;

        // Set the first value in the result buffer to the value of one
        resultBuffer[0] = 1;

        for (i = 1; i < length; i++) {
            // The sum of this value plus all the previous values in the buffer array
            runningSum += resultBuffer[i];

            // The current value is updated to be the current value multiplied by the index divided by the running sum value
            resultBuffer[i] *= i / runningSum;
        }
    }

    private void absoluteThreshold(final short[] wave) {
        // TODO
    }

    private void parabolicInterpolation(final short[] wave) {
        // TODO
    }

    private void bestLocalEstimate(final short[] wave) {
        // TODO
    }
}
