package com.xwl.mvvm.base.util;


/**
 * @ProjectName: GcService
 * @ClassName: UserUtil
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/14 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/14 11:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserUtil {

    private static String token = "";

    // 获取登录状态
    public static boolean getLogIn() {
        // TODO 需要开发者完善用户的登录状态
        return true;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        if (token != null) {
            UserUtil.token = token;
        }
    }
}
