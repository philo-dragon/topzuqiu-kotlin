package cn.com.topzuqiu.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.pfl.common.utils.AppManager;
import com.pfl.common.utils.RouteUtils;

import cn.com.topzuqiu.R;
import cn.com.topzuqiu.ui.activity.MainActivity;

@Route(path = RouteUtils.APP_SPLASH_ACTIVITY)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SPUtils.getInstance().getBoolean("isWelcome", false)) {
            RouteUtils.actionStart(RouteUtils.APP_WELCOME_ACTIVITY, R.anim.alpha_enter, R.anim.alpha_exit);
        } else {
            RouteUtils.actionStart(RouteUtils.APP_MAIN_ACTIVITY, R.anim.alpha_enter, R.anim.alpha_exit);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
