package com.xwl.mvvm.business.card;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @ProjectName: face_android_device
 * @Package: com.east.face.device.service.sysn
 * @ClassName: Record
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/4/13 10:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/13 10:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface CardProtocol {
    @Multipart
    @POST("deviceapp/clockIn")
    Call<JsonObject> upClockIn(@Part List<MultipartBody.Part> partLis);
}
