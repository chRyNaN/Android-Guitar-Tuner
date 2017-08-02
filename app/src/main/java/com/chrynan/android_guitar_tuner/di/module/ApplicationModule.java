package com.chrynan.android_guitar_tuner.di.module;

import android.app.Application;
import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link dagger.Module} for application level dependencies.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }
}
