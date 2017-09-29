package com.chrynan.android_guitar_tuner.tuner.note;

/**
 * A mutable note implementation of the {@link NoteMutator} interface.
 */
public class MutableNote implements NoteMutator {
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

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setFrequency(final double frequency) {
        this.frequency = frequency;
    }

    @Override
    public void setPercentOffset(final float percentOffset) {
        this.percentOffset = percentOffset;
    }
}
