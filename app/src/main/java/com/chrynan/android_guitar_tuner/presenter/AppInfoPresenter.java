package com.chrynan.android_guitar_tuner.presenter;

import android.content.Context;

import com.chrynan.android_guitar_tuner.di.ApplicationContext;
import com.chrynan.android_guitar_tuner.ui.view.AppInfoView;

public class AppInfoPresenter implements Presenter {

    @ApplicationContext
    private final Context context;
    private final AppInfoView view;

    public AppInfoPresenter(@ApplicationContext final Context context, final AppInfoView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void detachView() {
        // No Operation
    }
}
