package com.chrynan.android_guitar_tuner.di.module;

import android.content.Context;
import android.media.AudioManager;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.di.FragmentScope;
import com.chrynan.android_guitar_tuner.presenter.PitchPresenter;
import com.chrynan.android_guitar_tuner.tuner.GuitarPitchPlayer;
import com.chrynan.android_guitar_tuner.tuner.PitchPlayer;
import com.chrynan.android_guitar_tuner.tuner.config.AndroidAudioConfig;
import com.chrynan.android_guitar_tuner.tuner.config.AudioConfig;
import com.chrynan.android_guitar_tuner.tuner.converter.FrequencyConverter;
import com.chrynan.android_guitar_tuner.tuner.converter.SineWaveFrequencyConverter;
import com.chrynan.android_guitar_tuner.tuner.player.AndroidAudioPlayer;
import com.chrynan.android_guitar_tuner.tuner.player.AudioPlayer;
import com.chrynan.android_guitar_tuner.tuner.volume.AndroidVolumeObserver;
import com.chrynan.android_guitar_tuner.tuner.volume.VolumeObserver;
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
    AudioManager provideAudioManager(@ApplicationContext final Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Provides
    @FragmentScope
    AudioConfig provideAudioConfig() {
        return new AndroidAudioConfig();
    }

    @Provides
    @FragmentScope
    AudioPlayer provideAudioPlayer(final AudioConfig audioConfig) {
        return new AndroidAudioPlayer(audioConfig);
    }

    @Provides
    @FragmentScope
    FrequencyConverter provideFrequencyConverter(final AudioConfig audioConfig) {
        return new SineWaveFrequencyConverter(audioConfig);
    }

    @Provides
    @FragmentScope
    PitchPlayer providePitchPlayer(final AudioPlayer audioPlayer, final FrequencyConverter frequencyConverter) {
        return new GuitarPitchPlayer(audioPlayer, frequencyConverter);
    }

    @Provides
    @FragmentScope
    VolumeObserver provideVolumeObserver(final AudioManager audioManager) {
        return new AndroidVolumeObserver(audioManager);
    }

    @Provides
    @FragmentScope
    PitchPresenter providePitchPresenter(final PitchPlayer pitchPlayer, final VolumeObserver volumeObserver) {
        return new PitchPresenter(view, pitchPlayer, volumeObserver);
    }
}
