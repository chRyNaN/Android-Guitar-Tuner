package com.chrynan.android_guitar_tuner.di.component;

import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.di.module.PitchViewModule;
import com.chrynan.android_guitar_tuner.ui.fragment.PitchPlayerFragment;
import com.chrynan.android_guitar_tuner.ui.view.PitchView;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in a {@link PitchView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = PitchViewModule.class
)
@FragmentScope
public interface PitchViewComponent {

    void inject(PitchPlayerFragment view);
}
