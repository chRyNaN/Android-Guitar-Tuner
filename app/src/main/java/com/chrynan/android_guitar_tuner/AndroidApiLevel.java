package com.chrynan.android_guitar_tuner;

import java.util.Objects;

/**
 * A class that represents an Android API Level.
 */
public class AndroidApiLevel {

    private int versionCode;
    private AndroidVersion androidVersion;

    public AndroidApiLevel(int versionCode) {
        this.versionCode = versionCode;
        this.androidVersion = AndroidVersion.forVersionCode(versionCode);
    }

    /**
     * Retrieves the Android version code.
     *
     * @return The int representing the Android Version Code.
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * Retrieves the {@link AndroidVersion} represented by the {@link #versionCode}.
     *
     * @return The {@link AndroidVersion} of the Android Version Code.
     */
    public AndroidVersion getAndroidVersion() {
        return androidVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AndroidApiLevel androidApiLevel = (AndroidApiLevel) o;

        return versionCode == androidApiLevel.versionCode &&
                androidVersion == androidApiLevel.androidVersion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionCode, androidVersion);
    }
}
