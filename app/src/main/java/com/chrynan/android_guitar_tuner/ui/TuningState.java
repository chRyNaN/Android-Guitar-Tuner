package com.chrynan.android_guitar_tuner.ui;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An {@link IntDef} annotation that corresponds to the state of a tuner (in tune, out of tune, or
 * undefined).
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({TuningState.IN_TUNE, TuningState.OUT_OF_TUNE, TuningState.UNDEFINED})
public @interface TuningState {

    int IN_TUNE = 0;
    int OUT_OF_TUNE = 1;
    int UNDEFINED = 2;
}
