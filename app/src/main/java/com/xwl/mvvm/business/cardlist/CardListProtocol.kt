package com.xwl.mvvm.business.cardlist

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist
 * @ClassName: CardListProtocol
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 9:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 9:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface CardListProtocol {
    @GET("deviceapp/getActivationCode/{deviceSn}")
    fun getActivationCode(@Path("deviceSn") deviceSn: String): Observable<JsonObject?>?
}