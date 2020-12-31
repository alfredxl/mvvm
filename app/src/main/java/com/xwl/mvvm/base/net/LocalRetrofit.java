package com.xwl.mvvm.base.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xwl.mvvm.BuildConfig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    private static final String BASE_URL = "https://face.hk-ai.cn:8094";
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

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
                                Map<String, String> map = getHeaders();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Content-Type", "application/json;charset=UTF-8")
                                        .header("time", Objects.requireNonNull(map.get("time")))
                                        .header("User-Agent", Objects.requireNonNull(map.get("User-Agent")))
                                        .header("sign", Objects.requireNonNull(map.get("sign")))
                                        .header("token", "");
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

    public static Map<String, String> getHeaders() {
        String time = String.valueOf(System.currentTimeMillis());
        String userAgent = "Agent=PlanTimeApp.Time=" + time;
        String sign = sing(time, userAgent).toLowerCase();
        Map<String, String> map = new HashMap<>(3);
        map.put("time", time);
        map.put("User-Agent", userAgent);
        map.put("sign", sign);
        return map;
    }

    public static String sing(String time, String userAgent) {
        byte[] toSing = String.format("time=%s&User-Agent=%s&key=JU*&:876RTghvY[}$wes23+(*&0C8601A09F545DDDB5FFA2807FAD0739C25E6CB4", time, userAgent).getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            return new String(encodeHex(messageDigest.digest(toSing)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }
}
