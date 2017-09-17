package com.chrynan.android_guitar_tuner.tuner.converter;

/**
 * An interface for a class that converters byte or short arrays to a float array representing the
 * same values.
 */
public interface Converter {

    /**
     * Converts the provided byte array to a corresponding float array.
     *
     * @param array          The byte array to be converted.
     * @param convertedArray The float array representing the original byte array but as float values.
     */
    void convert(final byte[] array, final float[] convertedArray);

    /**
     * Converts the provided short array to a corresponding float array.
     *
     * @param array          The short array to be converted.
     * @param convertedArray The float array representing the original short array but as float values.
     */
    void convert(final short[] array, final float[] convertedArray);
}
