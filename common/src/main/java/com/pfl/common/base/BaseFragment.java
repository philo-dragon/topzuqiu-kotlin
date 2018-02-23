package com.pfl.common.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;

import com.pfl.common.di.AppComponent;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.common.weidget.TitleBar;
import com.pfl.component.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by rocky on 2017/12/27.
 */

public abstract class BaseFragment extends RxFragment implements IActivity {

    protected Activity mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContextView(), container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                componentInject(App.getInstance(BaseApplication.class).getAppComponent());
                initToolbar(view);
                initView(view);
                initEvent();
                initData();
            }
        });
    }

    private void initToolbar(View view) {
        if (view.findViewById(R.id.toolbar) != null) {
            TitleBar titleBar = view.findViewById(R.id.title_bar);
            titleBar.setImmersive(isImmersive());
            titleBar.setBackgroundColor(setBackGroundColor());
            if (isNeedBack()) {
                titleBar.setLeftImageResource(getLeftImageResource());
                titleBar.setLeftText("返回");
            }
            titleBar.setLeftTextColor(setLeftTextColor());
            titleBar.setTitle(getTitle());
            titleBar.setTitleColor(setTitleColor());
            titleBar.setDividerColor(setToolBarDividerColor());
        }
    }

    /**
     * is visible back button
     *
     * @return
     */
    protected boolean isNeedBack() {
        return true;
    }

    /**
     * set ivider color
     *
     * @return
     */
    protected int setToolBarDividerColor() {
        return Color.TRANSPARENT;
    }

    /**
     * set title color
     *
     * @return
     */
    protected int setTitleColor() {
        return Color.WHITE;
    }

    /**
     * set left text color
     *
     * @return
     */
    protected int setLeftTextColor() {
        return Color.WHITE;
    }

    /**
     * set background color
     *
     * @return
     */
    protected int setBackGroundColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    /**
     * set left image resid
     *
     * @return
     */
    protected int getLeftImageResource() {
        return R.mipmap.back_green;
    }

    /**
     * 是否沉浸式
     *
     * @return
     */
    protected boolean isImmersive() {
        return true;
    }

    protected String getTitle() {
        return "";
    }

}
