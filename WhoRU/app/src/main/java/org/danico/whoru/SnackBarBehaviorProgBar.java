package org.danico.whoru;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by nicomda on 25/3/17.
 */

public class SnackBarBehaviorProgBar extends CoordinatorLayout.Behavior<MaterialProgressBar> {
    public SnackBarBehaviorProgBar() {
    }

    public SnackBarBehaviorProgBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, MaterialProgressBar child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, MaterialProgressBar child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }
}
