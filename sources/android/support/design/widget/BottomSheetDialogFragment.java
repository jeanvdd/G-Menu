package android.support.design.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getActivity(), getTheme());
    }

    public void setupDialog(Dialog dialog, int style) {
        if (dialog instanceof BottomSheetDialog) {
            ((BottomSheetDialog) dialog).supportRequestWindowFeature(1);
        } else {
            super.setupDialog(dialog, style);
        }
    }
}
