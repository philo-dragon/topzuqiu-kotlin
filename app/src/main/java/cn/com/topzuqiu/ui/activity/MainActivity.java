package cn.com.topzuqiu.ui.activity;

import android.app.Activity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.AppManager;
import com.pfl.common.utils.RouteUtils;

import cn.com.topzuqiu.R;


@Route(path = RouteUtils.APP_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {


    @Override
    public int getContextView() {
        return R.layout.activity_main;
    }


    @Override
    public void componentInject(AppComponent appComponent) {
       /*DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule())
                .build()
                .inject(this);*/
    }

    @Override
    public void initView(View view) {

        for (Activity activity : AppManager.getAppManager().getActivityStack()) {
            if (!(activity instanceof MainActivity)) {
                AppManager.getAppManager().finishActivity(activity);
            }
        }

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    public void doLogin(View view) {
        RouteUtils.actionStart(RouteUtils.MODULE_USER_LOGIN_ACTIVITY);
    }

}

