package com.pfl.module_user.ui.login;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.component.R;
import com.pfl.module_user.utils.AccountPasswordUtil;

@Route(path = RouteUtils.MODULE_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {


    private ImageLoader imageLoader;
    private ImageView mCollectView;

    private TextInputLayout tilAccount;
    private TextInputLayout tilPassword;

    private EditText etAccount;
    private EditText etPassword;

    private Button btnSubmit;

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

        tilAccount = findViewById(R.id.til_account);
        tilPassword = findViewById(R.id.til_password);

        etAccount = findViewById(R.id.tv_account);
        etPassword = findViewById(R.id.tv_password);

        btnSubmit = findViewById(R.id.btn_submit);


        ImageView imgUser = findViewById(R.id.img_user);
        imageLoader.loadImage(this, ImageConfigImpl.
                builder().url("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg").
                imageView(imgUser).
                build());

        TitleBar titleBar = findViewById(R.id.title_bar);

        mCollectView = (ImageView) titleBar.addAction(new TitleBar.ImageAction(R.mipmap.collect) {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了收藏");
                mCollectView.setImageResource(R.mipmap.fabu);
            }
        });

        titleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了发布");
            }
        });

        AccountPasswordUtil.setAccount(etAccount, tilAccount);
        AccountPasswordUtil.setPassword(etPassword, tilPassword);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (AccountPasswordUtil.veriftyAccount(account.toString(), tilAccount) &&
                        AccountPasswordUtil.veriftyPassword(password.toString(), tilPassword)) {

                    ToastUtils.showShort("登录成功");
                    finishActivity();
                }
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    @Override
    public void finishActivity() {
        super.finishActivity();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}
