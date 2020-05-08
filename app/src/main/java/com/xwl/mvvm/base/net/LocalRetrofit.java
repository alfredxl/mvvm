package com.xwl.mvvm.base.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xwl.mvvm.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base.net
 * @ClassName: LocalRetrofit
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/7 15:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/7 15:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocalRetrofit {
    private static volatile Retrofit retrofit;
    private static final String BASE_URL = "https://api.apiopen.top";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (LocalRetrofit.class) {
                if (retrofit == null) {
                    //启用Log日志
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(chain -> {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Content-Type", "application/json;charset=UTF-8");
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }).connectTimeout(15, TimeUnit.SECONDS)
                            .addInterceptor(loggingInterceptor).build();
                    // 解决Date转换问题
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                            .serializeNulls()
                            .create();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
