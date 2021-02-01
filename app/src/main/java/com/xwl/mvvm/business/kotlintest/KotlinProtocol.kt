package com.xwl.mvvm.business.kotlintest

import com.google.gson.JsonObject
import com.xwl.mvvm.business.kotlintest.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.kotlintest
 * @ClassName: KotlinProtocol
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 10:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 10:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface KotlinProtocol {
    @POST("sys/login")
    fun sysLogin(@Body loginBean: LoginBean): Observable<JsonObject?>?
}