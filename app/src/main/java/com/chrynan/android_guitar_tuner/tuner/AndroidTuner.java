package com.chrynan.android_guitar_tuner.tuner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.chrynan.android_guitar_tuner.exception.MissingListenerException;
import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;

/**
 * An Android implementation of the {@link Tuner} interface.
 */
public class AndroidTuner implements Tuner {

    private static final int AUDIO_RECORD_AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;
    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    private final PitchDetector detector;
    private final NoteFinder finder;
    private final AudioRecord audioRecorder;
    private final int readSize;
    private final short[] buffer;

    private Tuner.Listener listener;

    private Thread recordingThread;

    private boolean record;

    public AndroidTuner(final AudioConfig audioConfig, final PitchDetector detector, final NoteFinder finder) {
        this.detector = detector;
        this.finder = finder;

        audioRecorder = new AudioRecord(AUDIO_RECORD_AUDIO_SOURCE, audioConfig.getSampleRate(),
                AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT, audioConfig.getBufferSize());
        readSize = audioConfig.getReadSize();
        buffer = new short[readSize];
    }

    @Override
    public void start() {
        if (listener == null) {
            throw new MissingListenerException(Tuner.Listener.class, AndroidTuner.class);
        }

        record = true;

        recordingThread = new Thread(() -> {
            while (record) {
                audioRecorder.read(buffer, 0, readSize);

                double frequency = detector.detect(buffer);

                finder.setFrequency(frequency);

                String noteName = finder.getNoteName();

                float percentageOffset = finder.getPercentageDifference();

                listener.onNote(noteName, frequency, percentageOffset);
            }
        });

        recordingThread.start();
    }

    @Override
    public void stop() {
        record = false;
        recordingThread = null;
        audioRecorder.stop();
    }

    @Override
    public void setListener(final Tuner.Listener listener) {
        this.listener = listener;
    }

    @Override
    public void removeListener() {
        this.listener = null;
    }
}
