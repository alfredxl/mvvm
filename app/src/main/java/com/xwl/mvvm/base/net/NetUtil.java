package com.xwl.mvvm.base.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.util
 * @ClassName: NetUtil
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/15 14:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/15 14:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NetUtil {
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
