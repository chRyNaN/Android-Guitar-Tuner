package com.chrynan.android_guitar_tuner.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.exception.MissingListenerException;

/**
 * A {@link DialogFragment} that displays more information on why the AUDIO_RECORD permission is
 * needed.
 */
public class PermissionRationalDialogFragment extends DialogFragment {

    public static final String TAG = "PermissionRationalDialogFragment";

    private DialogListener listener;

    public static PermissionRationalDialogFragment newInstance() {
        return new PermissionRationalDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (DialogListener.class.isInstance(getParentFragment())) {
            listener = (DialogListener) getParentFragment();
        } else if (DialogListener.class.isInstance(getActivity())) {
            listener = (DialogListener) getActivity();
        } else {
            Class<?> parentClazz = getParentFragment() != null ? getParentFragment().getClass() : getActivity().getClass();

            throw new MissingListenerException(DialogListener.class, parentClazz, PermissionRationalDialogFragment.class);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity(), R.style.PermissionDialog)
                .setTitle(R.string.permission_dialog_rationale_title)
                .setMessage(R.string.permission_dialog_rationale_desc)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    dialog.dismiss();
                    listener.onDialogCanceled();
                })
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    listener.onDialogProceed();
                })
                .setOnDismissListener(dialog -> listener.onDialogDismissed())
                .create();
    }

    public interface DialogListener {

        void onDialogProceed();

        void onDialogCanceled();

        void onDialogDismissed();
    }
}
