package com.pfl.module2.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.pfl.module2.di.module2.DaggerModule2Component;
import com.pfl.module2.di.module2.Module2Module;
import com.pfl.module2.mvp.module2.Module2Persenter;
import com.pfl.module2.mvp.module2.Module2View;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = RouteUtils.MODULE2_FRAGMENT)
public class Module2Fragment extends BaseFragment implements Module2View {

    private TextView textView;

    @Inject
    Module2Persenter persenter;

    private void requestData() {
        persenter.requestData();
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerModule2Component.builder()
                .appComponent(appComponent)
                .module2Module(new Module2Module(this, this))
                .build()
                .inject(this);
    }

    @Override
    public int getContextView() {
        return R.layout.fragment_module2;
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.textView);
    }

    @Override
    public void initData() {
        requestData();
    }

    @Override
    public void initEvent() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusBarUtil.darkMode(mContext, false);
            }
        });
    }

    @Override
    public void onSuccess(String token) {
        textView.setText(token);
    }

}
