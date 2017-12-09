package com.chrynan.android_guitar_tuner.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chrynan.android_guitar_tuner.AndroidVersion;
import com.chrynan.android_guitar_tuner.AppInfo;
import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.ui.view.AppInfoView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppInfoPresenter implements Presenter {

    @ApplicationContext
    private final Context context;
    private final AppInfoView view;
    private final SimpleDateFormat simpleDateFormat;

    public AppInfoPresenter(@ApplicationContext final Context context, final AppInfoView view) {
        this.context = context;
        this.view = view;
        this.simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
    }

    public void getAppInformation() {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            AppInfo appInfo = new AppInfo(packageInfo);

            view.showAppName(appInfo.getAppName());
            view.showPackageName(appInfo.getPackageName());
            view.showAPKLocation(appInfo.getApkLocation());
            view.showVersionName(appInfo.getVersionName());
            view.showVersionCode(String.valueOf(appInfo.getVersionCode()));
            view.showFirstInstallDate(simpleDateFormat.format(new Date(appInfo.getFirstInstallTime())));
            view.showLastUpdatedDate(simpleDateFormat.format(new Date(appInfo.getLastUpdatedTime())));

            AndroidVersion minVersion = appInfo.getMinAndroidVersion();
            AndroidVersion targetVersion = appInfo.getTargetAndroidVersion();
            AndroidVersion deviceVersion = appInfo.getDeviceAndroidVersion();

            // TODO show the actual API version in the String output

            view.showMinSDKVersion(minVersion.getName());
            view.showTargetSDKVersion(targetVersion.getName());
            view.showDeviceSDKVersion(deviceVersion.getName());
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Log Out Exception
            // TODO Display defaults/error
        }
    }

    @Override
    public void detachView() {
        // No Operation
    }
}