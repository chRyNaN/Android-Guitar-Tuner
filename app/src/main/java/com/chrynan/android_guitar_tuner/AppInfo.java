package com.chrynan.android_guitar_tuner;

import android.content.pm.PackageInfo;
import android.os.Build;

import java.util.Objects;

/**
 * A model class representing information about the current state of the application.
 */
public class AppInfo {

    private final String versionName;
    private final int versionCode;
    private final long firstInstallTime;
    private final long lastUpdatedTime;
    private final String appName;
    private final String packageName;
    private final AndroidApiLevel minAndroidApiLevel;
    private final AndroidApiLevel targetAndroidApiLevel;
    private final AndroidApiLevel deviceAndroidApiLevel;
    private final String apkLocation;

    public AppInfo(final PackageInfo packageInfo) {
        this.versionName = packageInfo.versionName;
        this.versionCode = packageInfo.versionCode;
        this.firstInstallTime = packageInfo.firstInstallTime;
        this.lastUpdatedTime = packageInfo.lastUpdateTime;
        this.appName = packageInfo.applicationInfo.name;
        this.packageName = packageInfo.applicationInfo.packageName;
        this.targetAndroidApiLevel = new AndroidApiLevel(packageInfo.applicationInfo.targetSdkVersion);
        this.deviceAndroidApiLevel = new AndroidApiLevel(Build.VERSION.SDK_INT);
        this.apkLocation = packageInfo.applicationInfo.sourceDir;
        this.minAndroidApiLevel = new AndroidApiLevel(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? packageInfo.applicationInfo.minSdkVersion : AndroidVersion.UNKNOWN.getEarliestVersionCode());
    }

    /**
     * Retrieves the version name of this application.
     *
     * @return The version name.
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Retrieves the version code of this application.
     *
     * @return The version code.
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * Retrieves the first known time this app was installed.
     *
     * @return The time, in milliseconds, that this app was first installed.
     */
    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    /**
     * Retrieves the last known time this app was updated.
     *
     * @return The time, in milliseconds, that this app was last updated.
     */
    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * Retrieves the name of this application.
     *
     * @return The name of this application.
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Retrieves the package name of this application.
     *
     * @return The package name of this application.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Retrieves the {@link AndroidApiLevel} representing the minimum version this application allows.
     *
     * @return The minimum {@link AndroidApiLevel}.
     */
    public AndroidApiLevel getMinAndroidApiLevel() {
        return minAndroidApiLevel;
    }

    /**
     * Retrieves the {@link AndroidApiLevel} representing the target version of this application.
     *
     * @return The target {@link AndroidApiLevel}.
     */
    public AndroidApiLevel getTargetAndroidApiLevel() {
        return targetAndroidApiLevel;
    }

    /**
     * Retrieves the {@link AndroidApiLevel} representing the current version on this device.
     *
     * @return The device's current {@link AndroidApiLevel}.
     */
    public AndroidApiLevel getDeviceAndroidApiLevel() {
        return deviceAndroidApiLevel;
    }

    /**
     * Retrieves the location of this application's installed APK.
     *
     * @return The APK location.
     */
    public String getApkLocation() {
        return apkLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AppInfo appInfo = (AppInfo) o;

        return versionCode == appInfo.versionCode &&
                firstInstallTime == appInfo.firstInstallTime &&
                lastUpdatedTime == appInfo.lastUpdatedTime &&
                Objects.equals(versionName, appInfo.versionName) &&
                Objects.equals(appName, appInfo.appName) &&
                Objects.equals(packageName, appInfo.packageName) &&
                minAndroidApiLevel == appInfo.minAndroidApiLevel &&
                targetAndroidApiLevel == appInfo.targetAndroidApiLevel &&
                deviceAndroidApiLevel == appInfo.deviceAndroidApiLevel &&
                Objects.equals(apkLocation, appInfo.apkLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionName, versionCode, firstInstallTime, lastUpdatedTime, appName,
                packageName, minAndroidApiLevel, targetAndroidApiLevel, deviceAndroidApiLevel, apkLocation);
    }
}
