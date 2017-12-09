package com.chrynan.android_guitar_tuner.presenter;

import com.chrynan.android_guitar_tuner.ui.view.AppInfoView;

public class AppInfoPresenter implements Presenter {

    private final AppInfoView view;

    public AppInfoPresenter(final AppInfoView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        // No Operation
    }
}
