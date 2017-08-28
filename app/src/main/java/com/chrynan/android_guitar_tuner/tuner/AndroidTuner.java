package com.chrynan.android_guitar_tuner.tuner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;

import io.reactivex.Observable;

/**
 * An Android implementation of the {@link Tuner} interface.
 */
public class AndroidTuner implements Tuner {

    private static final int AUDIO_RECORD_AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;
    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    private final MutableNote note = new MutableNote();

    private final Observable<Note> observable;
    private final AudioRecord audioRecorder;
    private final int readSize;
    private final short[] buffer;

    public AndroidTuner(final AudioConfig audioConfig, final PitchDetector detector, final NoteFinder finder) {
        this.audioRecorder = new AudioRecord(AUDIO_RECORD_AUDIO_SOURCE, audioConfig.getSampleRate(),
                AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT, audioConfig.getBufferSize());
        this.readSize = audioConfig.getReadSize();
        this.buffer = new short[readSize];

        observable = Observable.create(e -> {
            while (!e.isDisposed()) {
                audioRecorder.read(buffer, 0, readSize);

                double frequency = detector.detect(buffer);

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
        });
    }

    @Override
    public Observable<Note> startListening() {
        return observable.share();
    }
}
