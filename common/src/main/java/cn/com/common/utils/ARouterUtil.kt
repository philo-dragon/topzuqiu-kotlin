package cn.com.common.utils

import com.alibaba.android.arouter.launcher.ARouter
import android.support.v4.app.Fragment


/**
 * Created by rocky on 2018/2/9.
 */

class ARouterUtil {
    companion object {

        /**
         * app 模块
         */
        val APP_ACTIVITY_SPLASH = "/app/splash"


        /** ---------------- 启动Activity不带动画 ----------------- **/


        fun actionStart(path: String) {
            actionStart(path, mutableMapOf())
        }

        /**
         * 启动Activity
         * parameters 携带参数
         *
         * @param path
         */
        fun actionStart(path: String, parameters: MutableMap<String, String>) {

            var build = ARouter.getInstance().build(path)
            for ((k, v) in parameters) {
                build.withString(k, v)
            }

            build.navigation()
        }


        /** ---------------- 启动Activity带有动画 ---------------- **/


        /**
         * 启动Activity带动画
         *
         * @param path
         */
        fun actionStart(path: String, enterId: Int, exitId: Int) {
            actionStart(path, mutableMapOf(), enterId, exitId)
        }

        /**
         * 启动Activity
         * parameters 携带参数
         * enterId exitId 进入动画 退出动荡和
         * @param path
         */
        fun actionStart(path: String, parameters: MutableMap<String, String>, enterId: Int, exitId: Int) {
            var build = ARouter.getInstance().build(path)
            for ((k, v) in parameters) {
                build.withString(k, v)
            }
            build.withTransition(enterId, exitId)
            build.navigation()
        }


        /** ---------------- 获取Fragment ---------------- **/

        fun newFragment(path: String): Fragment {
            return newFragment(path, mutableMapOf())
        }


        fun newFragment(path: String, parameters: MutableMap<String, String>): Fragment {

            val build = ARouter.getInstance().build(path)

            for ((k, v) in parameters) {
                build.withString(k, v)
            }

            return build.navigation() as? Fragment ?: throw RuntimeException("path is not Fragment")
        }
    }
}
