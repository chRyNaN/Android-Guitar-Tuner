package com.chrynan.android_guitar_tuner.presenter;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.tuner.PitchPlayer;
import com.chrynan.android_guitar_tuner.tuner.volume.VolumeObserver;
import com.chrynan.android_guitar_tuner.tuner.volume.VolumeState;
import com.chrynan.android_guitar_tuner.ui.view.PitchView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PitchPresenter implements Presenter {

    private static final int RETRY_COUNT_MAX = 3;

    private final PitchView view;
    private final PitchPlayer pitchPlayer;
    private final VolumeObserver volumeObserver;

    private Disposable pitchPlayerDisposable;
    private Disposable volumeDisposable;

    private int retryCount = 0;

    public PitchPresenter(final PitchView view, final PitchPlayer pitchPlayer, final VolumeObserver volumeObserver) {
        this.view = view;
        this.pitchPlayer = pitchPlayer;
        this.volumeObserver = volumeObserver;
    }

    @Override
    public void detachView() {
        // No Operation
    }

    public void startPlayingNote(final double noteFrequency) {
        pitchPlayerDisposable = pitchPlayer.startPlaying(noteFrequency)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, error -> {
                    Timber.e(error, "Error Starting Pitch Playback.");

                    boolean showAction = retryCount < RETRY_COUNT_MAX;
                    int description = showAction ? R.string.pitch_player_error_playing_note_description_with_action
                            : R.string.pitch_player_error_playing_note_description_without_action;

                    view.onErrorPlayingNote(
                            description,
                            showAction,
                            R.string.pitch_player_error_playing_note_action,
                            R.color.snack_bar_action_color
                    );
                });
    }

    public void retryPlayingNote(final double noteFrequency) {
        retryCount++;
        startPlayingNote(noteFrequency);
    }

    public void stopPlayingNote() {
        if (pitchPlayerDisposable != null) {
            pitchPlayerDisposable.dispose();
        }
    }

    public void startListeningToVolumeChanges() {
        volumeDisposable = volumeObserver.startListening()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(state -> {
                    if (state == VolumeState.MUTED) {
                        view.onUpdateVolumeState(R.string.volume_state_muted, R.color.volume_muted);
                    } else {
                        view.onUpdateVolumeState(R.string.volume_state_on, R.color.volume_on);
                    }
                }, error -> {
                    Timber.e(error, "Error Starting to Listen to Volume Changes.");
                });
    }

    public void stopListeningToVolumeChanges() {
        if (volumeDisposable != null) {
            volumeDisposable.dispose();
        }
    }
}
