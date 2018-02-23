package com.pfl.common.weidget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yan.inflaterauto.AutoUtils;

public class AutoLayout extends LinearLayout {

    AutoLayout(Context context) {
        super(context);
    }

    protected AutoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        ViewGroup.LayoutParams vlp = super.generateLayoutParams(attrs);
        AutoUtils.autoLayout(vlp,getContext(),attrs);
        return super.generateLayoutParams(attrs);
    }
}