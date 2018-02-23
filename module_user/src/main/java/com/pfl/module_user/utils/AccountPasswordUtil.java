package com.pfl.module_user.utils;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.knifestone.hyena.currency.TextWatcherAdapter;

/**
 * 账户 密码 验证
 */
public class AccountPasswordUtil {

    private AccountPasswordUtil() {
    }

    public static boolean veriftyAccount(String acccount, TextInputLayout inputLayout) {

        boolean result = false;

        if (acccount.length() < 1) {
            inputLayout.setError("请输入账户");
            return result;
        }
        if (acccount.length() > 20) {
            inputLayout.setError("账户不能大于20个字符");
            return result;
        } else {
            inputLayout.setError(null);
            result = true;
        }

        return result;
    }

    public static boolean veriftyPassword(String password, TextInputLayout inputLayout) {

        boolean result = false;

        if (password.length() < 1) {
            inputLayout.setError("请输入密码");
            return result;
        }
        if (password.length() < 6) {
            inputLayout.setError("密码不能少于6个字符");
            return result;
        } else {
            inputLayout.setError(null);
        }
        if (password.length() > 18) {
            inputLayout.setError("密码不能大于18个字符");
            return result;
        } else {
            inputLayout.setError(null);
            result = true;
        }

        return result;
    }


    public static void setAccount(EditText text, final TextInputLayout inputLayout) {
        text.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                veriftyAccount(s.toString(), inputLayout);
            }
        });
    }

    public static void setPassword(EditText text, final TextInputLayout inputLayout) {
        text.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                veriftyPassword(s.toString(), inputLayout);
            }
        });
    }

}
