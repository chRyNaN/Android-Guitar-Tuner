package com.chrynan.android_guitar_tuner.tuner.note;

import java.util.HashMap;
import java.util.Map;

/**
 * A Map based implementation of the {@link FrequencyFinder} interface.
 */
public class MapFrequencyFinder implements FrequencyFinder {

    private final Map<NoteName, Double> notes = new HashMap<>();

    public MapFrequencyFinder() {
        notes.put(NoteName.A, 110.0);
        notes.put(NoteName.A_SHARP, 116.5);
        notes.put(NoteName.B, 123.5);
        notes.put(NoteName.C, 130.8);
        notes.put(NoteName.C_SHARP, 138.6);
        notes.put(NoteName.D, 146.8);
        notes.put(NoteName.D_SHARP, 155.6);
        notes.put(NoteName.E, 164.8);
        notes.put(NoteName.F, 174.6);
        notes.put(NoteName.F_SHARP, 185.0);
        notes.put(NoteName.G, 196.0);
        notes.put(NoteName.G_SHARP, 207.6);
        notes.put(NoteName.UNDEFINED, 0.0);
    }

    @Override
    public double getFrequency(NoteName name) {
        return notes.get(name);
    }
}
