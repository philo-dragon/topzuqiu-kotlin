package com.pfl.common.base;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;
import com.pfl.common.di.AppComponent;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.common.weidget.TitleBar;
import com.pfl.component.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yan.inflaterauto.InflaterAuto;

public abstract class BaseActivity<T> extends RxAppCompatActivity implements IActivity {

    protected T mBinding;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(InflaterAuto.wrap(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isSupportDataBindind()) {
            mBinding = dataBinding();
        } else {
            setContentView(getContextView());
        }
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView(getWindow().getDecorView());
    }

    public boolean isSupportDataBindind() {
        return false;
    }

    /**
     * DataBinding
     */
    public T dataBinding() {
        if (isSupportDataBindind()) {
            return (T) DataBindingUtil.setContentView(this, getContextView());
        } else {
            return null;
        }
    }


}