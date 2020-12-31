package com.xwl.mvvm.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.base.util
 * @ClassName: DateFormatUtils
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/31 10:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/31 10:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DateFormatUtils {
    /*** to yyyyMMddHHmmssz ***/
    public static String getFormatToStringYmdsz(Date date) {
        return getSimpleDateFormatYmdsz().format(date);
    }

    private static SimpleDateFormat getSimpleDateFormatYmdsz() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    }
}
