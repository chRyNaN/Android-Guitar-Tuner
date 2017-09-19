package com.chrynan.android_guitar_tuner.ui.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.chrynan.android_guitar_tuner.GuitarTunerApplication;
import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.di.component.DaggerTunerViewComponent;
import com.chrynan.android_guitar_tuner.di.module.TunerViewModule;
import com.chrynan.android_guitar_tuner.exception.MissingListenerException;
import com.chrynan.android_guitar_tuner.presenter.TunerPresenter;
import com.chrynan.android_guitar_tuner.ui.view.TunerView;
import com.chrynan.android_guitar_tuner.ui.widget.CircleTunerView;

import javax.inject.Inject;

import butterknife.BindView;

public class CircleGuitarTunerFragment extends BaseFragment implements TunerView, CircleTunerView.OnNotePressedListener {

    private static final int ANIMATOR_DURATION = 200;
    private static final int ANIMATOR_DELAY = 0;
    private static final Interpolator ANIMATOR_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @BindView(R.id.circleTunerView)
    CircleTunerView circleTunerView;

    @Inject
    TunerPresenter presenter;

    private final ValueAnimator tunerAnimator = ValueAnimator.ofFloat(0, 0);

    private OnPlayNoteListener listener;

    private float currentAnimationValue;

    public static CircleGuitarTunerFragment newInstance() {
        return new CircleGuitarTunerFragment();
    }

    public CircleGuitarTunerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (OnPlayNoteListener.class.isInstance(getParentFragment())) {
            listener = (OnPlayNoteListener) getParentFragment();
        } else if (OnPlayNoteListener.class.isInstance(getActivity())) {
            listener = (OnPlayNoteListener) getActivity();
        } else {
            Class<?> parentClazz = getParentFragment() != null ? getParentFragment().getClass() : getActivity().getClass();

            throw new MissingListenerException(OnPlayNoteListener.class, parentClazz, CircleGuitarTunerFragment.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflateAndBindView(inflater, R.layout.fragment_circle_guitar_tuner, container, false);

        circleTunerView.setOnNotePressedListener(this);

        tunerAnimator.setDuration(ANIMATOR_DURATION);
        tunerAnimator.setStartDelay(ANIMATOR_DELAY);
        tunerAnimator.setInterpolator(ANIMATOR_INTERPOLATOR);

        // Set the initial pointer position to zero if this is the first time loading the view
        if (savedInstanceState == null) {
            circleTunerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    circleTunerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    circleTunerView.updateNote(null, 0);
                }
            });
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.startListeningForNotes();
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.stopListeningForNotes();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    protected void setupDaggerComponent() {
        DaggerTunerViewComponent.builder()
                .applicationComponent(GuitarTunerApplication.getApplicationComponent())
                .tunerViewModule(new TunerViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onShowNote(final String noteName, final float percentOffset) {
        tunerAnimator.cancel();
        tunerAnimator.removeAllUpdateListeners();

        tunerAnimator.addUpdateListener(animation -> {
            currentAnimationValue = (float) animation.getAnimatedValue();

            circleTunerView.updateNote(noteName, currentAnimationValue);
        });

        tunerAnimator.setFloatValues(currentAnimationValue, percentOffset);
        tunerAnimator.start();
    }

    @Override
    public void onPlayNote(final String noteName, final double frequency, final float x, final float y) {
        listener.onPlayNote(noteName, frequency, x, y);
    }

    @Override
    public void onNotePressed(final CircleTunerView.NotePosition notePosition) {
        presenter.notePressed(notePosition.getName(), notePosition.getX(), notePosition.getY());
    }

    public interface OnPlayNoteListener {
        void onPlayNote(String noteName, double frequency, float x, float y);
    }
}
