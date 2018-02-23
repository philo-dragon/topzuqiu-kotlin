package com.pfl.common.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class RouteUtils {

    /**
     * app 模块
     */

    //Actiity
    public static final String APP_SPLASH_ACTIVITY = "/app/splash";
    public static final String APP_WELCOME_ACTIVITY = "/app/welcome";
    public static final String APP_MAIN_ACTIVITY = "/app/main";

    /**
     * module_user 模块
     */

    // Activity

    // Fragment
    public static final String MODULE_USER_LOGIN_ACTIVITY = "/module_user/login";

    //Fragment
    public static final String APP_HOME_FRAGMENT = "/app/homeFragment";
    public static final String MODULE_USER_FRAGMENT = "/module_user/userFragment";
    public static final String SERVICE_USER = "/module_user/service_user";

    /**
     * module1 模块
     */
    //module1
    public static final String MODULE1_FRAGMENT = "/module1/fragment";

    /**
     * module2 模块
     */
    //module2
    public static final String MODULE2_FRAGMENT = "/module2/fragment";


    /** ===================================================================================================================================  **/


    /** ====================== 启动Activity不带动画 ============================= **/
    /**
     * 启动Activity
     *
     * @param path
     */
    public static void actionStart(String path) {
        actionStart(path, new HashMap<String, String>());
    }


    /**
     * 启动Activity
     * parameters 携带参数
     *
     * @param path
     */
    public static void actionStart(String path, Map<String, String> parameters) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        build.navigation();
    }


    /** ====================== 启动Activity带有动画 ============================= **/

    /**
     * 启动Activity
     * enterId 进入动画
     * exitId 退出动画
     *
     * @param path
     */
    public static void actionStart(String path, int enterId, int exitId) {
        actionStart(path, new HashMap<String, String>(), enterId, exitId);
    }

    /**
     * 启动Activity
     * parameters 携带参数
     * enterId 进入动画
     * exitId 退出动画
     *
     * @param path
     */
    public static void actionStart(String path, Map<String, String> parameters, int enterId, int exitId) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        build.withTransition(enterId, exitId);
        build.navigation();
    }


    /** ====================== 获取Fragment ============================= **/

    /**
     * 启动Activity
     *
     * @param path
     */
    public static Fragment newFragment(String path) {
        return newFragment(path, new HashMap<String, String>());
    }


    /**
     * 启动Activity
     * parameters 携带参数
     *
     * @param path
     */
    public static Fragment newFragment(String path, Map<String, String> parameters) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        Object navigation = build.navigation();
        if (!(navigation instanceof Fragment)) {
            throw new RuntimeException("path is not Fragment");
        }

        return (Fragment) navigation;
    }
}
