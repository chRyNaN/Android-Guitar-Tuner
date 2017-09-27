package com.chrynan.android_guitar_tuner.tuner.note;

/**
 * An Enum representing the name of a note.
 */
public enum NoteName {

    A("A"),
    A_SHARP("A♯"),
    B("B"),
    C("C"),
    C_SHARP("C♯"),
    D("D"),
    D_SHARP("D♯"),
    E("E"),
    F("F"),
    F_SHARP("F♯"),
    G("G"),
    G_SHARP("G♯"),
    UNDEFINED("Undefined");

    /**
     * Retrieves a {@link NoteName} object represented by the provided String name.
     *
     * @param name The String name.
     * @return A {@link NoteName} representing the provided String name.
     */
    public static NoteName getFor(final String name) {
        if (name != null) {
            String updatedName = name.trim().toUpperCase().replace('#', '♯');

            try {
                for (NoteName noteName : values()) {
                    if (noteName.getName().equals(updatedName)) {
                        return noteName;
                    }
                }
            } catch (IllegalArgumentException e) {
                // No operation
            }
        }

        return UNDEFINED;
    }

    private String name;

    NoteName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
