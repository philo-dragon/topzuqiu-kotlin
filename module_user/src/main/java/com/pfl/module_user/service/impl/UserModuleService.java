package com.pfl.module_user.service.impl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.service.IUserModeleService;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.constant.UserInfoManager;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

/**
 * ARouter跨模块的服务调用
 * 提供其它模块获取用户信息使用
 */
@Route(path = RouteUtils.SERVICE_USER)
public class UserModuleService implements IUserModeleService {

    @Override
    public UserInfo getUserInfo() {
        return UserInfoManager.getInstance().getUserInfo();
    }

    @Override
    public void init(Context context) {

    }
}
