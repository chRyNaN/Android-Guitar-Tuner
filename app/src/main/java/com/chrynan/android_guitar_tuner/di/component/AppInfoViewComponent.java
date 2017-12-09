package com.chrynan.android_guitar_tuner.di.component;

import com.chrynan.android_guitar_tuner.di.ActivityScope;
import com.chrynan.android_guitar_tuner.di.module.AppInfoViewModule;
import com.chrynan.android_guitar_tuner.ui.activity.AppInfoActivity;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in an
 * {@link com.chrynan.android_guitar_tuner.ui.view.AppInfoView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = AppInfoViewModule.class
)
@ActivityScope
public interface AppInfoViewComponent {

    void inject(AppInfoActivity view);
}
