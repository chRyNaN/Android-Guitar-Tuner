package com.chrynan.android_guitar_tuner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    protected Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDaggerComponent();
    }

    protected View inflateAndBindView(@NonNull LayoutInflater inflater, @LayoutRes int layoutRes, ViewGroup container, boolean attachToRoot) {
        View v = inflater.inflate(layoutRes, container, attachToRoot);

        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    protected void setupDaggerComponent() {
        // Default is no operation
    }
}
