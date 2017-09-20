package com.chrynan.android_guitar_tuner.presenter;

import com.chrynan.android_guitar_tuner.tuner.Tuner;
import com.chrynan.android_guitar_tuner.tuner.note.FrequencyFinder;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TunerPresenter implements Presenter {

    private final TunerView view;
    private final Tuner tuner;
    private final FrequencyFinder frequencyFinder;

    private Disposable disposable;

    public TunerPresenter(final TunerView view, final Tuner tuner, final FrequencyFinder frequencyFinder) {
        this.view = view;
        this.tuner = tuner;
        this.frequencyFinder = frequencyFinder;
    }

    @Override
    public void detachView() {
        // No Operation
    }

    public void startListeningForNotes() {
        disposable = tuner.startListening()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(note -> view.onShowNote(note.getName(), note.getPercentOffset()));
    }

    public void stopListeningForNotes() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void notePressed(final String noteName, final float x, final float y) {
        view.onPlayNote(noteName, frequencyFinder.getFrequency(FrequencyFinder.NoteName.getFor(noteName)), x, y);
    }
}
