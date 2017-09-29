package com.chrynan.android_guitar_tuner.tuner;

import com.chrynan.android_guitar_tuner.tuner.note.Note;

import io.reactivex.Observable;

/**
 * An interface for all tuner implementations.
 */
public interface Tuner {

    /**
     * Subscribes to the tuner for notes found as a result of a pitch detection algorithm.
     *
     * @return An {@link Observable<Note>} providing all the found {@link Note}s for as long as subscribed.
     */
    Observable<Note> startListening();
}
