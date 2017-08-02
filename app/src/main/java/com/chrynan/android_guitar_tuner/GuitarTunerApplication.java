package com.chrynan.android_guitar_tuner;

import android.app.Application;

import com.chrynan.android_guitar_tuner.di.component.ApplicationComponent;
import com.chrynan.android_guitar_tuner.di.component.DaggerApplicationComponent;
import com.chrynan.android_guitar_tuner.di.module.ApplicationModule;

public class GuitarTunerApplication extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }
}
