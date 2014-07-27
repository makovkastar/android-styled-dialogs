package eu.inmite.android.lib.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    private boolean isChecked;
    private OnCheckedChangeListener onCheckedChangeListener;
    private final List<Checkable> checkableViews = new ArrayList<Checkable>();

    /**
     * Interface definition for a callback to be invoked when the checked state of a CheckableRelativeLayout changed.
     */
    public static interface OnCheckedChangeListener {
        public void onCheckedChanged(CheckableRelativeLayout layout, boolean isChecked);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, int checkableId) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (isCheckable(child)) {
                this.checkableViews.add((Checkable) child);
            }
        }
    }

    private boolean isCheckable(View view) {
        return view instanceof Checkable;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        for (Checkable c : checkableViews) {
            c.setChecked(isChecked);
        }

        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
        for (Checkable c : checkableViews) {
            c.toggle();
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
