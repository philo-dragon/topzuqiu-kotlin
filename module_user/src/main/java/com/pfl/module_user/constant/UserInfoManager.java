package com.pfl.module_user.constant;

import com.pfl.common.entity.module_user.UserInfo;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class UserInfoManager {

    private static UserInfoManager INSTANCE;
    private static UserInfo userInfo;

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {

        if (null == INSTANCE) {
            synchronized (UserInfoManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new UserInfoManager();
                }
            }
        }

        return INSTANCE;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
