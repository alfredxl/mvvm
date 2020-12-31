package com.xwl.mvvm.business.card

import com.xwl.mvvm.base.mvvm.BusinessBaseModel
import com.xwl.mvvm.base.net.LocalRetrofit
import com.xwl.mvvm.base.util.BaseResponseFormat
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.card
 * @ClassName: CardModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/31 8:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/31 8:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardModel : BusinessBaseModel() {
    fun clock(clockInTime: String, deviceSn: String, bitmaps: ByteArray): Boolean {
        val fileName = "byte_2_17140_${System.currentTimeMillis() / 1000}.jpg"
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)//表单类型
        val body = RequestBody.create(MediaType.parse("multipart/form-data"), bitmaps)
        builder.addFormDataPart("clockInPicture", fileName, body)
        val userId = "17140"
        val uuid = UUID.randomUUID().toString()
        val identity = "2"
        val schoolId = "2"
        builder.addFormDataPart("clockInTime", clockInTime)
        builder.addFormDataPart("deviceSn", deviceSn)
        builder.addFormDataPart("userId", userId)
        builder.addFormDataPart("uuid", uuid)
        builder.addFormDataPart("identity", identity)
        builder.addFormDataPart("schoolId", schoolId)
        val parts = builder.build().parts()
        try {
            LocalRetrofit.getRetrofit().create(CardProtocol::class.java).upClockIn(parts)
                    .execute().body()?.let { resultBean ->
                        val result = BaseResponseFormat.getFormatBean<String>(resultBean, String::class.java)
                        return result.code == 200
                    }
        } catch (e: Exception) {
        }
        return false
    }

    fun toByteArray(`is`: InputStream?): ByteArray {
        if (`is` == null) {
            return ByteArray(0)
        }
        val buffer = ByteArrayOutputStream()
        var read: Int
        val data = ByteArray(4096)
        try {
            while (`is`.read(data, 0, data.size).also { read = it } != -1) {
                buffer.write(data, 0, read)
            }
        } catch (ignored: java.lang.Exception) {
            return ByteArray(0)
        } finally {
            try {
                buffer.close()
            } catch (ignored: IOException) {
            }
            try {
                `is`.close()
            } catch (ignored: IOException) {
            }
        }
        return buffer.toByteArray()
    }
}