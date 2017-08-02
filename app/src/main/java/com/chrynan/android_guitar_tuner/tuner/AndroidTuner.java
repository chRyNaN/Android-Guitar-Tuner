package com.chrynan.android_guitar_tuner.tuner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.chrynan.android_guitar_tuner.exception.MissingListenerException;
import com.chrynan.android_guitar_tuner.tuner.detection.PitchDetector;
import com.chrynan.android_guitar_tuner.tuner.note.NoteFinder;

public class AndroidTuner implements Tuner {

    private static final int AUDIO_RECORD_AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;
    private static final int AUDIO_RECORD_SAMPLE_RATE = 44100;
    private static final int AUDIO_RECORD_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    private static final int AUDIO_RECORD_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private static final int AUDIO_RECORD_BUFFER_SIZE = AudioRecord.getMinBufferSize(AUDIO_RECORD_SAMPLE_RATE,
            AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
    private static final int AUDIO_RECORD_READ_SIZE = AUDIO_RECORD_BUFFER_SIZE / 4;


    private final AudioRecord audioRecorder = new AudioRecord(AUDIO_RECORD_AUDIO_SOURCE, AUDIO_RECORD_SAMPLE_RATE,
            AUDIO_RECORD_CHANNEL_CONFIG, AUDIO_RECORD_AUDIO_FORMAT, AUDIO_RECORD_BUFFER_SIZE);

    private final short[] buffer = new short[AUDIO_RECORD_READ_SIZE];

    private final PitchDetector detector;
    private final NoteFinder finder;

    private TunerListener listener;

    private Thread recordingThread;

    private boolean record;

    public AndroidTuner(final PitchDetector detector, final NoteFinder finder) {
        this.detector = detector;
        this.finder = finder;
    }

    @Override
    public void start() {
        if (listener == null) {
            throw new MissingListenerException(TunerListener.class, AndroidTuner.class);
        }

        record = true;

        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (record) {
                    audioRecorder.read(buffer, 0, AUDIO_RECORD_READ_SIZE);

                    double frequency = detector.detect(buffer);

                    finder.setFrequency(frequency);

                    String noteName = finder.getNoteName();

                    float percentageOffset = finder.getPercentageDifference();

                    listener.onNote(noteName, frequency, percentageOffset);
                }
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
    public void setTunerListener(final TunerListener listener) {
        this.listener = listener;
    }

    @Override
    public void removeTunerListener() {
        this.listener = null;
    }
}
