package com.pfl.module_user.ui.login;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;
import com.pfl.component.databinding.ActivityLoginBinding;

@Route(path = RouteUtils.MODULE_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private ImageLoader imageLoader;

    @Override
    public void componentInject(AppComponent appComponent) {
        imageLoader = appComponent.getImageLoader();
    }

    @Override
    public int getContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {

        imageLoader.loadImage(this, ImageConfigImpl.
                builder().url("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg").
                imageView(mBinding.imgUser).
                build());


    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean isSupportDataBindind() {
        return true;
    }
}
