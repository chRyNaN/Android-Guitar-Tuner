package com.chrynan.android_guitar_tuner.di.component;

import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.di.module.TunerViewModule;
import com.chrynan.android_guitar_tuner.ui.fragment.CircleGuitarTunerFragment;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in a {@link TunerView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = TunerViewModule.class
)
@FragmentScope
public interface TunerViewComponent {

    void inject(CircleGuitarTunerFragment view);
}
