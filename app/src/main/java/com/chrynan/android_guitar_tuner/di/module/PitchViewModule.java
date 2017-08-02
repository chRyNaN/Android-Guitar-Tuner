package com.chrynan.android_guitar_tuner.di.module;

import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.presenter.PitchPresenter;
import com.chrynan.android_guitar_tuner.ui.view.PitchView;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link Module} used for dependency injection in a {@link PitchView} implementation.
 */
@Module
public class PitchViewModule {

    private final PitchView view;

    public PitchViewModule(final PitchView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    PitchPresenter providePitchPresenter(@ApplicationContext Context context) {
        return new PitchPresenter(view, context);
    }
}
