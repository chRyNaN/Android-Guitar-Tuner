package com.chrynan.android_guitar_tuner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chrynan.android_guitar_tuner.GuitarTunerApplication;
import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.di.component.DaggerAppInfoViewComponent;
import com.chrynan.android_guitar_tuner.di.module.AppInfoViewModule;
import com.chrynan.android_guitar_tuner.presenter.AppInfoPresenter;
import com.chrynan.android_guitar_tuner.ui.view.AppInfoView;
import com.chrynan.android_guitar_tuner.ui.widget.TwoLineCell;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppInfoActivity extends AppCompatActivity implements AppInfoView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appNameTwoLineCell)
    TwoLineCell appNameTwoLineCell;
    @BindView(R.id.packageNameTwoLineCell)
    TwoLineCell packageNameTwoLineCell;
    @BindView(R.id.apkLocationTwoLineCell)
    TwoLineCell apkLocationTwoLineCell;
    @BindView(R.id.firstInstallTwoLineCell)
    TwoLineCell firstInstallDateTwoLineCell;
    @BindView(R.id.lastUpdatedTwoLineCell)
    TwoLineCell lastUpdatedDateTwoLineCell;
    @BindView(R.id.versionNameTwoLineCell)
    TwoLineCell versionNameTwoLineCell;
    @BindView(R.id.versionCodeTwoLineCell)
    TwoLineCell versionCodeTwoLineCell;
    @BindView(R.id.minSDKVersionTwoLineCell)
    TwoLineCell minSDKVersionTwoLineCell;
    @BindView(R.id.targetSDKVersionTwoLineCell)
    TwoLineCell targetSDKVersionTwoLineCell;
    @BindView(R.id.deviceSDKVersionTwoLineCell)
    TwoLineCell deviceSDKVersionTwoLineCell;

    @Inject
    AppInfoPresenter presenter;

    public static Intent newIntent(final Context context) {
        return new Intent(context, AppInfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDaggerComponent();

        setContentView(R.layout.activity_app_info);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter.getAppInformation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setTitle(getString(R.string.app_info_appbar_title));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    protected void setupDaggerComponent() {
        DaggerAppInfoViewComponent.builder()
                .applicationComponent(GuitarTunerApplication.getApplicationComponent())
                .appInfoViewModule(new AppInfoViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showAppName(final String appName) {
        appNameTwoLineCell.setSecondLineTextView(appName);
    }

    @Override
    public void showPackageName(final String packageName) {
        packageNameTwoLineCell.setSecondLineTextView(packageName);
    }

    @Override
    public void showAPKLocation(final String location) {
        apkLocationTwoLineCell.setSecondLineTextView(location);
    }

    @Override
    public void showFirstInstallDate(final String firstInstall) {
        firstInstallDateTwoLineCell.setSecondLineTextView(firstInstall);
    }

    @Override
    public void showLastUpdatedDate(final String lastUpdated) {
        lastUpdatedDateTwoLineCell.setSecondLineTextView(lastUpdated);
    }

    @Override
    public void showVersionName(final String versionName) {
        versionNameTwoLineCell.setSecondLineTextView(versionName);
    }

    @Override
    public void showVersionCode(final String versionCode) {
        versionCodeTwoLineCell.setSecondLineTextView(versionCode);
    }

    @Override
    public void showMinSDKVersion(final String minSDKVersion) {
        minSDKVersionTwoLineCell.setSecondLineTextView(minSDKVersion);
    }

    @Override
    public void showTargetSDKVersion(final String targetSDKVersion) {
        targetSDKVersionTwoLineCell.setSecondLineTextView(targetSDKVersion);
    }

    @Override
    public void showDeviceSDKVersion(final String deviceSDKVersion) {
        deviceSDKVersionTwoLineCell.setSecondLineTextView(deviceSDKVersion);
    }

    @OnClick(R.id.openSourceCodeTwoLineCell)
    void onOpenSourceCodeClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_info_link_code))));
    }

    @OnClick(R.id.appStoreTwoLineCell)
    void onAppStoreClick() {
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.share_app_link))));
    }

    @OnClick(R.id.issuesTwoLineCell)
    void onIssuesClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_info_link_issue))));
    }

    @OnClick(R.id.licenseTwoLineCell)
    void onLicenseClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_info_link_license))));
    }
}