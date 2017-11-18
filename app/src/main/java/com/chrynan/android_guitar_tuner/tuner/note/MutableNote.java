package com.chrynan.android_guitar_tuner.tuner.note;

import java.util.Objects;

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

    MutableNote(final String name, final double frequency, final float percentOffset) {
        this.name = name;
        this.frequency = frequency;
        this.percentOffset = percentOffset;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableNote note = (MutableNote) o;
        return Double.compare(note.frequency, frequency) == 0 &&
                Float.compare(note.percentOffset, percentOffset) == 0 &&
                Objects.equals(name, note.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frequency, percentOffset);
    }
}
