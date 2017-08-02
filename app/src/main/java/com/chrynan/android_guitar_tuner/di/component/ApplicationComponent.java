package com.chrynan.android_guitar_tuner.di.component;

import android.content.Context;

import com.chrynan.android_guitar_tuner.GuitarTunerApplication;
import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A Dagger {@link Component} that provides global dependencies.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context getApplicationContext();

    void inject(GuitarTunerApplication application);
}
