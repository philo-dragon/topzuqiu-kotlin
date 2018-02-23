package cn.com.topzuqiu.base;

import com.blankj.utilcode.util.Utils;
import com.pfl.common.base.BaseApplication;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.module_user.constant.UserInfoManager;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class ComponentApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("潘飞龙");
        userInfo.setMobileNum("13488747197");
        UserInfoManager.getInstance().setUserInfo(userInfo);
    }
}
