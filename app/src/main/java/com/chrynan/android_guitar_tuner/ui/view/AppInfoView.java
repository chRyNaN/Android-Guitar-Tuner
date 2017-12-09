package com.chrynan.android_guitar_tuner.ui.view;

/**
 * A View interface for a view implementation that displays information about the app.
 */
public interface AppInfoView {

    void showAppName(String appName);

    void showPackageName(String packageName);

    void showAPKLocation(String location);

    void showFirstInstallDate(String firstInstall);

    void showLastUpdatedDate(String lastUpdated);

    void showVersionName(String versionName);

    void showVersionCode(String versionCode);

    void showMinSDKVersion(String minSDKVersion);

    void showTargetSDKVersion(String targetSDKVersion);

    void showDeviceSDKVersion(String deviceSDKVersion);
}
