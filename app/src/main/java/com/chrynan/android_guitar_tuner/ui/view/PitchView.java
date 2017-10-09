package com.chrynan.android_guitar_tuner.ui.view;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

/**
 * A View interface for a view implementation that reacts to a note being played.
 */
public interface PitchView {

    void onUpdateVolumeState(@StringRes int volumeStateText, @ColorRes int textColor);
}
