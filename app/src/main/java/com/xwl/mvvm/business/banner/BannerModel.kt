package com.xwl.mvvm.business.banner

import com.xwl.mvvm.base.mvvm.BusinessBaseModel

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.banner
 * @ClassName: BannerModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/11 15:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/11 15:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class BannerModel : BusinessBaseModel() {
    fun getData(): List<String> {
        return arrayListOf(
                "https://east-face.oss-cn-shanghai.aliyuncs.com/facedeviceapp/190309153658147087.mp4",
                "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg",
                "https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg",
                "https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg",
                "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
                "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg")
    }
}