package com.chrynan.android_guitar_tuner.di.module;

import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.presenter.TunerPresenter;
import com.chrynan.android_guitar_tuner.tuner.AndroidTuner;
import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.ArrayNoteFinder;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link Module} used for dependency injection in a {@link TunerView} implementation.
 */
@Module
public class TunerViewModule {

    private final TunerView view;

    public TunerViewModule(TunerView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    PitchDetector providePitchDetector() {
        // TODO
        return null;
    }

    @Provides
    @FragmentScope
    NoteFinder provideNoteFinder() {
        return new ArrayNoteFinder();
    }

    @Provides
    @FragmentScope
    Tuner provideTuner(PitchDetector detector, NoteFinder finder) {
        return new AndroidTuner(detector, finder);
    }

    @Provides
    @FragmentScope
    TunerPresenter provideTunerPresenter(@ApplicationContext Context context, Tuner tuner) {
        return new TunerPresenter(view, context, tuner);
    }
}
