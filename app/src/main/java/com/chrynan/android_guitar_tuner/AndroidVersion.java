package com.chrynan.android_guitar_tuner;

import android.os.Build;

/**
 * An enum class representing the different Android Versions.
 */
public enum AndroidVersion {

    DONUT(Constants.EARLIEST_VERSION_CODE_DONUT, Constants.LATEST_VERSION_CODE_DONUT, Constants.NAME_DONUT),
    ECLAIR(Constants.EARLIEST_VERSION_CODE_ECLAIR, Constants.LATEST_VERSION_CODE_ECLAIR, Constants.NAME_ECLAIR),
    FROYO(Constants.EARLIEST_VERSION_CODE_FROYO, Constants.LATEST_VERSION_CODE_FROYO, Constants.NAME_FROYO),
    GINGERBREAD(Constants.EARLIEST_VERSION_CODE_GINGERBREAD, Constants.LATEST_VERSION_CODE_GINGERBREAD, Constants.NAME_GINGERBREAD),
    HONEYCOMB(Constants.EARLIEST_VERSION_CODE_HONEYCOMB, Constants.LATEST_VERSION_CODE_HONEYCOMB, Constants.NAME_HONEYCOMB),
    ICE_CREAM_SANDWICH(Constants.EARLIEST_VERSION_CODE_ICE_CREAM_SANDWICH, Constants.LATEST_VERSION_CODE_ICE_CREAM_SANDWICH, Constants.NAME_ICE_CREAM_SANDWICH),
    JELLY_BEAN(Constants.EARLIEST_VERSION_CODE_JELLY_BEAN, Constants.LATEST_VERSION_CODE_JELLY_BEAN, Constants.NAME_JELLY_BEAN),
    KITKAT(Constants.EARLIEST_VERSION_CODE_KITKAT, Constants.LATEST_VERSION_CODE_KITKAT, Constants.NAME_KITKAT),
    LOLLIPOP(Constants.EARLIEST_VERSION_CODE_LOLLIPOP, Constants.LATEST_VERSION_CODE_LOLLIPOP, Constants.NAME_LOLLIPOP),
    MARSHMALLOW(Constants.EARLIEST_VERSION_CODE_MARSHMALLOW, Constants.LATEST_VERSION_CODE_MARSHMALLOW, Constants.NAME_MARSHMALLOW),
    NOUGAT(Constants.EARLIEST_VERSION_CODE_NOUGAT, Constants.LATEST_VERSION_CODE_NOUGAT, Constants.NAME_NOUGAT),
    OREO(Constants.EARLIEST_VERSION_CODE_OREO, Constants.LATEST_VERSION_CODE_OREO, Constants.NAME_OREO),
    NEWER_THAN_OREO(Constants.EARLIEST_VERSION_CODE_NEWER_THAN_OREO, Constants.LATEST_VERSION_CODE_NEWER_THAN_OREO, Constants.NAME_NEWER_THAN_OREO),
    OLDER_THAN_DONUT(Constants.EARLIEST_VERSION_CODE_OLDER_THAN_DONUT, Constants.LATEST_VERSION_CODE_OLDER_THAN_DONUT, Constants.NAME_OLDER_THAN_DONUT),
    UNKNOWN(Constants.EARLIEST_VERSION_CODE_UNKNOWN, Constants.LATEST_VERSION_CODE_UNKOWN, Constants.NAME_UNKNOWN);

    public static AndroidVersion forVersionCode(final int versionCode) {
        for (AndroidVersion version : values()) {
            if (version.isInVersion(versionCode)) {
                return version;
            }
        }

        return UNKNOWN;
    }

    private final int earliestVersionCode;
    private final int latestVersionCode;
    private final String name;

    AndroidVersion(final int earliestVersionCode, final int latestVersionCode, final String name) {
        this.earliestVersionCode = earliestVersionCode;
        this.latestVersionCode = latestVersionCode;
        this.name = name;
    }

    /**
     * Retrieves the earliest version code of this {@link AndroidVersion}. This value is inclusive
     * in this {@link AndroidVersion}.
     *
     * @return The earliest version code.
     */
    public int getEarliestVersionCode() {
        return earliestVersionCode;
    }

    /**
     * Retrieves the latest version code of this {@link AndroidVersion}. This value is inclusive in
     * this {@link AndroidVersion}.
     *
     * @return The latest version code.
     */
    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    /**
     * Retrieves the name of this {@link AndroidVersion}.
     *
     * @return The {@link String} name of this {@link AndroidVersion}.
     */
    public String getName() {
        return name;
    }

    /**
     * Determines whether the provided version code represents this {@link AndroidVersion}.
     *
     * @param versionCode The version code.
     * @return True if the provided version code is within this version, false otherwise.
     */
    public boolean isInVersion(final int versionCode) {
        return getEarliestVersionCode() <= versionCode && versionCode <= getLatestVersionCode();
    }

    private static class Constants {

        private static final String NAME_DONUT = "Donut";
        private static final String NAME_ECLAIR = "Eclair";
        private static final String NAME_FROYO = "Froyo";
        private static final String NAME_GINGERBREAD = "Gingerbread";
        private static final String NAME_HONEYCOMB = "HoneyComb";
        private static final String NAME_ICE_CREAM_SANDWICH = "Ice Cream Sandwich";
        private static final String NAME_JELLY_BEAN = "Jelly Bean";
        private static final String NAME_KITKAT = "Kitkat";
        private static final String NAME_LOLLIPOP = "Lollipop";
        private static final String NAME_MARSHMALLOW = "Marshmallow";
        private static final String NAME_NOUGAT = "Nougat";
        private static final String NAME_OREO = "Oreo";
        private static final String NAME_NEWER_THAN_OREO = "Newer than Oreo";
        private static final String NAME_OLDER_THAN_DONUT = "Older than Donut";
        private static final String NAME_UNKNOWN = "Unknown";

        private static final int EARLIEST_VERSION_CODE_DONUT = Build.VERSION_CODES.DONUT;
        private static final int EARLIEST_VERSION_CODE_ECLAIR = Build.VERSION_CODES.ECLAIR;
        private static final int EARLIEST_VERSION_CODE_FROYO = Build.VERSION_CODES.FROYO;
        private static final int EARLIEST_VERSION_CODE_GINGERBREAD = Build.VERSION_CODES.GINGERBREAD;
        private static final int EARLIEST_VERSION_CODE_HONEYCOMB = Build.VERSION_CODES.HONEYCOMB;
        private static final int EARLIEST_VERSION_CODE_ICE_CREAM_SANDWICH = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        private static final int EARLIEST_VERSION_CODE_JELLY_BEAN = Build.VERSION_CODES.JELLY_BEAN;
        private static final int EARLIEST_VERSION_CODE_KITKAT = Build.VERSION_CODES.KITKAT;
        private static final int EARLIEST_VERSION_CODE_LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;
        private static final int EARLIEST_VERSION_CODE_MARSHMALLOW = Build.VERSION_CODES.M;
        private static final int EARLIEST_VERSION_CODE_NOUGAT = Build.VERSION_CODES.N;
        private static final int EARLIEST_VERSION_CODE_OREO = Build.VERSION_CODES.O;
        private static final int EARLIEST_VERSION_CODE_NEWER_THAN_OREO = EARLIEST_VERSION_CODE_OREO + 1;
        private static final int EARLIEST_VERSION_CODE_OLDER_THAN_DONUT = 0;
        private static final int EARLIEST_VERSION_CODE_UNKNOWN = Integer.MIN_VALUE;

        private static final int LATEST_VERSION_CODE_DONUT = Build.VERSION_CODES.DONUT;
        private static final int LATEST_VERSION_CODE_ECLAIR = Build.VERSION_CODES.ECLAIR;
        private static final int LATEST_VERSION_CODE_FROYO = Build.VERSION_CODES.FROYO;
        private static final int LATEST_VERSION_CODE_GINGERBREAD = Build.VERSION_CODES.GINGERBREAD_MR1;
        private static final int LATEST_VERSION_CODE_HONEYCOMB = Build.VERSION_CODES.HONEYCOMB_MR2;
        private static final int LATEST_VERSION_CODE_ICE_CREAM_SANDWICH = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
        private static final int LATEST_VERSION_CODE_JELLY_BEAN = Build.VERSION_CODES.JELLY_BEAN_MR2;
        private static final int LATEST_VERSION_CODE_KITKAT = Build.VERSION_CODES.KITKAT_WATCH;
        private static final int LATEST_VERSION_CODE_LOLLIPOP = Build.VERSION_CODES.LOLLIPOP_MR1;
        private static final int LATEST_VERSION_CODE_MARSHMALLOW = Build.VERSION_CODES.M;
        private static final int LATEST_VERSION_CODE_NOUGAT = Build.VERSION_CODES.N_MR1;
        private static final int LATEST_VERSION_CODE_OREO = Build.VERSION_CODES.O;
        private static final int LATEST_VERSION_CODE_NEWER_THAN_OREO = Integer.MAX_VALUE;
        private static final int LATEST_VERSION_CODE_OLDER_THAN_DONUT = LATEST_VERSION_CODE_DONUT - 1;
        private static final int LATEST_VERSION_CODE_UNKOWN = -1;
    }
}
