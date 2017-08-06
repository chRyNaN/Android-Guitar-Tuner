package com.chrynan.android_guitar_tuner.di.module;

import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.presenter.TunerPresenter;
import com.chrynan.android_guitar_tuner.tuner.AndroidAudioConfig;
import com.chrynan.android_guitar_tuner.tuner.AndroidTuner;
import com.chrynan.android_guitar_tuner.tuner.AudioConfig;
import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.detection.YINPitchDetector;
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
    AudioConfig provideAudioConfig() {
        return new AndroidAudioConfig();
    }

    @Provides
    @FragmentScope
    PitchDetector providePitchDetector(AudioConfig audioConfig) {
        return new YINPitchDetector(audioConfig);
    }

    @Provides
    @FragmentScope
    NoteFinder provideNoteFinder() {
        return new ArrayNoteFinder();
    }

    @Provides
    @FragmentScope
    Tuner provideTuner(AudioConfig audioConfig, PitchDetector detector, NoteFinder finder) {
        return new AndroidTuner(audioConfig, detector, finder);
    }

    @Provides
    @FragmentScope
    TunerPresenter provideTunerPresenter(Tuner tuner) {
        return new TunerPresenter(view, tuner);
    }
}
