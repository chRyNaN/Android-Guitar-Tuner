package com.chrynan.android_guitar_tuner.di.module;

import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.presenter.TunerPresenter;
import com.chrynan.android_guitar_tuner.tuner.config.AndroidAudioConfig;
import com.chrynan.android_guitar_tuner.tuner.GuitarTuner;
import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;
import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.tuner.converter.Converter;
import com.chrynan.android_guitar_tuner.tuner.converter.PCMConverter;
import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.detection.YINPitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.ArrayNoteFinder;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;
import com.chrynan.android_guitar_tuner.tuner.recorder.AndroidAudioRecorder;
import com.chrynan.android_guitar_tuner.tuner.recorder.AudioRecorder;
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
    Converter provideConverter() {
        return new PCMConverter();
    }

    @Provides
    @FragmentScope
    AudioRecorder provideAudioRecorder(AudioConfig audioConfig, Converter converter) {
        return new AndroidAudioRecorder(audioConfig, converter);
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
    Tuner provideTuner(AudioRecorder audioRecorder, PitchDetector detector, NoteFinder finder) {
        return new GuitarTuner(audioRecorder, detector, finder);
    }

    @Provides
    @FragmentScope
    TunerPresenter provideTunerPresenter(Tuner tuner) {
        return new TunerPresenter(view, tuner);
    }
}
