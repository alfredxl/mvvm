package com.xwl.mvvm.base.util;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xwl.mvvm.base.net.BaseResponse;

import java.lang.reflect.Type;


/**
 * @ProjectName: huike-iot-andriod
 * @ClassName: BaseResponseFormat
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/6/20 15:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/6/20 15:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResponseFormat {
    private static final Gson gson = new Gson();

    public static <D> BaseResponse<D> getFormatBean(JsonObject jsonObject, Type mType) {
        if (jsonObject != null) {
            try {
                BaseResponse<D> baseResultBean = new BaseResponse<>();
                baseResultBean.setCode(jsonObject.get("code").getAsInt());
                baseResultBean.setMsg(jsonObject.get("msg").getAsString());
                if (jsonObject.has("data")) {
                    return getFormatBean(baseResultBean, mType, jsonObject.get("data"));
                } else {
                    return baseResultBean;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static <D> BaseResponse<D> getFormatBean(BaseResponse<D> baseResultBean, Type mType, JsonElement jsonObject) {
        if (baseResultBean != null) {
            if (baseResultBean.getCode() == 200) {
                try {
                    D data = gson.fromJson(jsonObject.toString(), mType);
                    baseResultBean.setData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    baseResultBean.setData(null);
                }
            } else {
                baseResultBean.setData(null);
            }
        }
        return baseResultBean;
    }
}
