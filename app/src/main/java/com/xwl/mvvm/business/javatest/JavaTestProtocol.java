package com.xwl.mvvm.business.javatest;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.javatest
 * @ClassName: JavaTestProtocol
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface JavaTestProtocol {
    @GET("https://api.apiopen.top/getSingleJoke")
    Observable<JsonObject> getIpAddress(@Query("sid") String id);
}
