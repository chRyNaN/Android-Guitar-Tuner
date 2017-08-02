package com.chrynan.android_guitar_tuner.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.chrynan.android_guitar_tuner.R;
import com.chrynan.android_guitar_tuner.exception.MissingListenerException;
import com.chrynan.android_guitar_tuner.ui.fragment.CircleGuitarTunerFragment;

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
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_desc)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.onDialogCanceled();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.onDialogProceed();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        listener.onDialogDismissed();
                    }
                })
                .create();
    }

    public interface DialogListener {

        void onDialogProceed();

        void onDialogCanceled();

        void onDialogDismissed();
    }
}
