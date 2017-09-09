package com.chrynan.android_guitar_tuner.tuner;

import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;
import com.chrynan.android_guitar_tuner.tuner.recorder.AudioRecorder;

import io.reactivex.Observable;

/**
 * An implementation of the {@link Tuner} interface.
 */
public class GuitarTuner implements Tuner {

    private final MutableNote note = new MutableNote();

    private final Observable<Note> observable;

    public GuitarTuner(final AudioRecorder audioRecorder, final PitchDetector detector, final NoteFinder finder) {

        // Initialize the Observable to be for listening to notes
        observable = Observable.create(e -> {
            audioRecorder.startRecording();

            while (!e.isDisposed()) {
                double frequency = detector.detect(audioRecorder.readNext());

                finder.setFrequency(frequency);

                // Since the note object has mutable fields and we return the same instance,
                // lock the object while updating its data
                synchronized (note) {
                    note.setFrequency(frequency);
                    note.setName(finder.getNoteName());
                    note.setPercentOffset(finder.getPercentageDifference());
                }

                e.onNext(note);
            }

            audioRecorder.stopRecording();
        });
    }

    @Override
    public Observable<Note> startListening() {
        return observable.share();
    }
}
