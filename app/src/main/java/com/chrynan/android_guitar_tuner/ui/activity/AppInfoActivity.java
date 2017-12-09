package com.chrynan.android_guitar_tuner.ui.activity;

import android.content.Context;
import android.content.Intent;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppInfoActivity extends AppCompatActivity implements AppInfoView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
}
