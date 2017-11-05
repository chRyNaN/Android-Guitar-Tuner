package com.chrynan.android_guitar_tuner.ui.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * A {@link android.transition.Transition} that performs a Material Design Circular Reveal
 * Animation.
 */
public class CircularRevealTransition extends Visibility {

    private int x;
    private int y;

    public CircularRevealTransition() {
        // Default Constructor
    }

    @Override
    public CircularRevealTransition setDuration(long duration) {
        super.setDuration(duration);

        return this;
    }

    @Override
    public CircularRevealTransition setStartDelay(long delay) {
        super.setStartDelay(delay);

        return this;
    }

    @Override
    public CircularRevealTransition setInterpolator(TimeInterpolator interpolator) {
        super.setInterpolator(interpolator);

        return this;
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        if (view != null) {
            int width = view.getWidth();
            int height = view.getHeight();

            float radius = (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));

            return new NoPauseAnimatorWrapper(ViewAnimationUtils.createCircularReveal(view, x, y, 0, radius));
        }

        return null;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        if (view != null) {
            int width = view.getWidth();
            int height = view.getHeight();

            float radius = (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));

            return new NoPauseAnimatorWrapper(ViewAnimationUtils.createCircularReveal(view, x, y, radius, 0));
        }

        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    public CircularRevealTransition setCenter(final int x, final int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
