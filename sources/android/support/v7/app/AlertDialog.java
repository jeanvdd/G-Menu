package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.appcompat.C0370R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    private AlertController mAlert;

    public static class Builder {
        /* renamed from: P */
        private final AlertParams f16P;
        private int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(Context context, int theme) {
            this.f16P = new AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, theme)));
            this.mTheme = theme;
        }

        public Context getContext() {
            return this.f16P.mContext;
        }

        public Builder setTitle(int titleId) {
            this.f16P.mTitle = this.f16P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.f16P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(View customTitleView) {
            this.f16P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(int messageId) {
            this.f16P.mMessage = this.f16P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.f16P.mMessage = message;
            return this;
        }

        public Builder setIcon(int iconId) {
            this.f16P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            this.f16P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(int attrId) {
            TypedValue out = new TypedValue();
            this.f16P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.f16P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(int textId, OnClickListener listener) {
            this.f16P.mPositiveButtonText = this.f16P.mContext.getText(textId);
            this.f16P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            this.f16P.mPositiveButtonText = text;
            this.f16P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, OnClickListener listener) {
            this.f16P.mNegativeButtonText = this.f16P.mContext.getText(textId);
            this.f16P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.f16P.mNegativeButtonText = text;
            this.f16P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(int textId, OnClickListener listener) {
            this.f16P.mNeutralButtonText = this.f16P.mContext.getText(textId);
            this.f16P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            this.f16P.mNeutralButtonText = text;
            this.f16P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f16P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.f16P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.f16P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.f16P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int itemsId, OnClickListener listener) {
            this.f16P.mItems = this.f16P.mContext.getResources().getTextArray(itemsId);
            this.f16P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, OnClickListener listener) {
            this.f16P.mItems = items;
            this.f16P.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
            this.f16P.mAdapter = adapter;
            this.f16P.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
            this.f16P.mCursor = cursor;
            this.f16P.mLabelColumn = labelColumn;
            this.f16P.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f16P.mItems = this.f16P.mContext.getResources().getTextArray(itemsId);
            this.f16P.mOnCheckboxClickListener = listener;
            this.f16P.mCheckedItems = checkedItems;
            this.f16P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            this.f16P.mItems = items;
            this.f16P.mOnCheckboxClickListener = listener;
            this.f16P.mCheckedItems = checkedItems;
            this.f16P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
            this.f16P.mCursor = cursor;
            this.f16P.mOnCheckboxClickListener = listener;
            this.f16P.mIsCheckedColumn = isCheckedColumn;
            this.f16P.mLabelColumn = labelColumn;
            this.f16P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem, OnClickListener listener) {
            this.f16P.mItems = this.f16P.mContext.getResources().getTextArray(itemsId);
            this.f16P.mOnClickListener = listener;
            this.f16P.mCheckedItem = checkedItem;
            this.f16P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
            this.f16P.mCursor = cursor;
            this.f16P.mOnClickListener = listener;
            this.f16P.mCheckedItem = checkedItem;
            this.f16P.mLabelColumn = labelColumn;
            this.f16P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
            this.f16P.mItems = items;
            this.f16P.mOnClickListener = listener;
            this.f16P.mCheckedItem = checkedItem;
            this.f16P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
            this.f16P.mAdapter = adapter;
            this.f16P.mOnClickListener = listener;
            this.f16P.mCheckedItem = checkedItem;
            this.f16P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(OnItemSelectedListener listener) {
            this.f16P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            this.f16P.mView = null;
            this.f16P.mViewLayoutResId = layoutResId;
            this.f16P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f16P.mView = view;
            this.f16P.mViewLayoutResId = 0;
            this.f16P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.f16P.mView = view;
            this.f16P.mViewLayoutResId = 0;
            this.f16P.mViewSpacingSpecified = true;
            this.f16P.mViewSpacingLeft = viewSpacingLeft;
            this.f16P.mViewSpacingTop = viewSpacingTop;
            this.f16P.mViewSpacingRight = viewSpacingRight;
            this.f16P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.f16P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.f16P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(this.f16P.mContext, this.mTheme, false);
            this.f16P.apply(dialog.mAlert);
            dialog.setCancelable(this.f16P.mCancelable);
            if (this.f16P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.f16P.mOnCancelListener);
            dialog.setOnDismissListener(this.f16P.mOnDismissListener);
            if (this.f16P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.f16P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    protected AlertDialog(Context context) {
        this(context, resolveDialogTheme(context, 0), true);
    }

    protected AlertDialog(Context context, int theme) {
        this(context, theme, true);
    }

    AlertDialog(Context context, int theme, boolean createThemeContextWrapper) {
        super(context, resolveDialogTheme(context, theme));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, resolveDialogTheme(context, 0));
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
        this.mAlert = new AlertController(context, this, getWindow());
    }

    static int resolveDialogTheme(Context context, int resid) {
        if (resid >= ViewCompat.MEASURED_STATE_TOO_SMALL) {
            return resid;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(C0370R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, null, msg);
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, null);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue out = new TypedValue();
        getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
