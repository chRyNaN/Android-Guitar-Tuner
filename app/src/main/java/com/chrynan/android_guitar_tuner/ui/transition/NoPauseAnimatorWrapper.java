package com.chrynan.android_guitar_tuner.ui.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * A wrapper around an {@link android.animation.Animator} that doesn't handle the pause and resume
 * functionality. This is because Android's Circular Reveal Animator throws an exception on pause,
 * causing the app to crash.
 */
public class NoPauseAnimatorWrapper extends Animator {

    private final Animator animator;

    NoPauseAnimatorWrapper(@NonNull final Animator animator) {
        this.animator = animator;
    }

    @Override
    public long getStartDelay() {
        return animator.getStartDelay();
    }

    @Override
    public void setStartDelay(long startDelay) {
        animator.setStartDelay(startDelay);
    }

    @Override
    public Animator setDuration(long duration) {
        return animator.setDuration(duration);
    }

    @Override
    public long getDuration() {
        return animator.getDuration();
    }

    @Override
    public void setInterpolator(TimeInterpolator value) {
        animator.setInterpolator(value);
    }

    @Override
    public TimeInterpolator getInterpolator() {
        return animator.getInterpolator();
    }

    @Override
    public boolean isRunning() {
        return animator.isRunning();
    }

    @Override
    public void start() {
        animator.start();
    }

    @Override
    public void end() {
        animator.end();
    }

    @Override
    public void cancel() {
        animator.cancel();
    }

    @Override
    public void pause() {
        // No Operation
    }

    @Override
    public void resume() {
        // No Operation
    }

    @Override
    public void addPauseListener(AnimatorPauseListener listener) {
        // No Operation
    }

    @Override
    public void removePauseListener(AnimatorPauseListener listener) {
        // No Operation
    }

    @Override
    public void addListener(AnimatorListener listener) {
        NoPauseAnimatorListener wrappedListener = new NoPauseAnimatorListener(this, listener);

        animator.addListener(wrappedListener);
    }

    @Override
    public ArrayList<AnimatorListener> getListeners() {
        return animator.getListeners();
    }

    @Override
    public void removeListener(AnimatorListener listener) {
        NoPauseAnimatorListener wrappedListener = new NoPauseAnimatorListener(this, listener);

        animator.removeListener(wrappedListener);
    }

    @Override
    public void removeAllListeners() {
        animator.removeAllListeners();
    }

    @Override
    public long getTotalDuration() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return animator.getTotalDuration();
        }

        return (animator.getDuration() == -1) ? -1 : animator.getStartDelay() + animator.getDuration();
    }

    @Override
    public boolean isStarted() {
        return animator.isStarted();
    }

    @Override
    public void setupStartValues() {
        animator.setupStartValues();
    }

    @Override
    public void setupEndValues() {
        animator.setupEndValues();
    }

    @Override
    public void setTarget(@Nullable Object target) {
        animator.setTarget(target);
    }

    @Override
    public Animator clone() {
        try {
            final Animator anim = super.clone();

            for (AnimatorListener l : getListeners()) {
                anim.addListener(l);
            }

            return anim;
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    private static class NoPauseAnimatorListener extends AnimatorListenerAdapter {

        private final Animator wrappedAnimator;
        private final AnimatorListener animatorListener;

        NoPauseAnimatorListener(final Animator wrappedAnimator, final AnimatorListener animatorListener) {
            this.wrappedAnimator = wrappedAnimator;
            this.animatorListener = animatorListener;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            animatorListener.onAnimationCancel(wrappedAnimator);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animatorListener.onAnimationEnd(wrappedAnimator);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            animatorListener.onAnimationRepeat(wrappedAnimator);
        }

        @Override
        public void onAnimationStart(Animator animation) {
            animatorListener.onAnimationStart(wrappedAnimator);
        }

        @Override
        public void onAnimationPause(Animator animation) {
            // No Operation
        }

        @Override
        public void onAnimationResume(Animator animation) {
            // No Operation
        }
    }
}
