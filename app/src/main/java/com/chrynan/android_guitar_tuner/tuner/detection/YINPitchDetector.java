package com.chrynan.android_guitar_tuner.tuner.detection;

import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;

/**
 * A {@link PitchDetector} implementation that uses a YIN algorithm to determine the frequency of
 * the provided waveform data. The YIN algorithm is similar to the Auto-correlation Function used
 * for pitch detection but adds additional steps to better the accuracy of the results. Each step
 * lowers the error rate further. The following implementation was inspired by
 * <a href="https://github.com/JorenSix/TarsosDSP/blob/master/src/core/be/tarsos/dsp/pitch/Yin.java">TarsosDsp</a>
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
    // According to the YIN Paper, the threshold should be between 0.10 and 0.15
    private static final float ABSOLUTE_THRESHOLD = 0.125f;

    private final double sampleRate;
    private final float[] resultBuffer;

    public YINPitchDetector(final AudioConfig audioConfig) {
        this.sampleRate = audioConfig.getSampleRate();
        this.resultBuffer = new float[audioConfig.getReadSize() / 2];
    }

    @Override
    public double detect(float[] wave) {
        int tau;

        // First, perform the functions to normalize the wave data

        // The first and second steps in the YIN algorithm
        autoCorrelationDifference(wave);

        // The third step in the YIN algorithm
        cumulativeMeanNormalizedDifference();

        // Then perform the functions to retrieve the tau (the approximate period)

        // The fourth step in the YIN algorithm
        tau = absoluteThreshold();

        // The fifth step in the YIN algorithm
        float betterTau = parabolicInterpolation(tau);

        // TODO implement the sixth and final step of the YIN algorithm
        // (it isn't implemented in the Tarsos DSP project but is briefly explained in the YIN
        // paper).

        // The fundamental frequency (note frequency) is the sampling rate divided by the tau (index
        // within the resulting buffer array that marks the period).
        // The period is the duration (or index here) of one cycle.
        // Frequency = 1 / Period, with respect to the sampling rate, Frequency = Sample Rate / Period
        return sampleRate / betterTau;
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
    private void autoCorrelationDifference(final float[] wave) {
        // Note this algorithm is currently slow (O(n^2)). Should look for any possible optimizations.
        int length = resultBuffer.length;
        int i, j;

        for (j = 1; j < length; j++) {
            for (i = 0; i < length; i++) {
                // d sub t (tau) = (x(i) - x(i - tau))^2, from i = 1 to result buffer size
                resultBuffer[j] += Math.pow((wave[i] - wave[i + j]), 2);
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

    /**
     * Performs step four of the YIN Algorithm on the {@link #resultBuffer}. This is the first step
     * in the algorithm to attempt finding the period of the wave data. When attempting to determine
     * the period of a wave, it's common to search for the high or low peaks or dips of the wave.
     * This will allow you to determine the length of a cycle or its period. However, especially
     * with a natural sound sample, it is possible to have false dips. This makes determining the
     * period more difficult. This function attempts to resolve this issue by introducing a
     * threshold. The result of this function yields an even lower rate (about 0.78% from about
     * 1.69%).
     *
     * @return The tau indicating the approximate period.
     */
    private int absoluteThreshold() {
        int tau;
        int length = resultBuffer.length;

        // The first two values in the result buffer should be 1, so start at the third value
        for (tau = 2; tau < length; tau++) {
            // If we are less than the threshold, continue on until we find the lowest value
            // indicating the lowest dip in the wave since we first crossed the threshold.
            if (resultBuffer[tau] < ABSOLUTE_THRESHOLD) {
                while (tau + 1 < length && resultBuffer[tau + 1] < resultBuffer[tau]) {
                    tau++;
                }

                // We have the approximate tau value, so break the loop
                break;
            }
        }

        // Some implementations of this algorithm set the tau value to -1 to indicate no correct tau
        // value was found. This implementation will just return the last tau.
        tau = tau >= length ? length - 1 : tau;

        return tau;
    }

    /**
     * Further lowers the error rate by using parabolas to smooth the wave between the minimum and
     * maximum points. Especially helps to detect higher frequencies more precisely. The result of
     * this function results in only a small error rate decline from about 0.78% to about 0.77%.
     */
    private float parabolicInterpolation(final int currentTau) {
        // Finds the points to fit the parabola between
        int x0 = currentTau < 1 ? currentTau : currentTau - 1;
        int x2 = currentTau + 1 < resultBuffer.length ? currentTau + 1 : currentTau;

        // Finds the better tau estimate
        float betterTau;

        if (x0 == currentTau) {
            if (resultBuffer[currentTau] <= resultBuffer[x2]) {
                betterTau = currentTau;
            } else {
                betterTau = x2;
            }
        } else if (x2 == currentTau) {
            if (resultBuffer[currentTau] <= resultBuffer[x0]) {
                betterTau = currentTau;
            } else {
                betterTau = x0;
            }
        } else {
            // Fit the parabola between the first point, current tau, and the last point to find a
            // better tau estimate.
            float s0 = resultBuffer[x0];
            float s1 = resultBuffer[currentTau];
            float s2 = resultBuffer[x2];

            betterTau = currentTau + (s2 - s0) / (2 * (2 * s1 - s2 - s0));
        }

        return betterTau;
    }
}
