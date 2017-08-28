package com.chrynan.android_guitar_tuner.tuner;

/**
 * A package-protected mutable implementation of the {@link com.chrynan.android_guitar_tuner.tuner.Tuner.Note} interface.
 */
public class MutableNote implements Tuner.Note {
    private String name;
    private double frequency;
    private float percentOffset;

    public MutableNote() {
        // Default constructor
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getFrequency() {
        return frequency;
    }

    @Override
    public float getPercentOffset() {
        return percentOffset;
    }

    void setName(final String name) {
        this.name = name;
    }

    void setFrequency(final double frequency) {
        this.frequency = frequency;
    }

    void setPercentOffset(final float percentOffset) {
        this.percentOffset = percentOffset;
    }
}
