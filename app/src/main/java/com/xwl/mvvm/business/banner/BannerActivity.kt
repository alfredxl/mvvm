package com.xwl.mvvm.business.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.business.banner.weight.NumIndicator
import com.xwl.mvvm.databinding.BannerActivityBinding
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.banner_activity.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.banner
 * @ClassName: BannerActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/11 15:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/11 15:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class BannerActivity : BusinessBaseActivity<BannerModel, BannerActivityBinding, BannerViewModel>() {
    var player: StandardGSYVideoPlayer? = null

    override fun initView() {
        val data = viewModel.getData()
        banner.addBannerLifecycleObserver(this)
                .setIndicator(NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .setAdapter(object : BannerAdapter<String, RecyclerView.ViewHolder>(data) {
                    override fun getItemViewType(position: Int): Int {
                        return if (data[position].endsWith(".mp4")) 1 else 0
                    }

                    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
                        val view = if (1 == viewType) {
                            LayoutInflater.from(this@BannerActivity).inflate(R.layout.video_item, parent, false)
                        } else {
                            LayoutInflater.from(this@BannerActivity).inflate(R.layout.image_item, parent, false)
                        }
                        return object : RecyclerView.ViewHolder(view) {}
                    }

                    override fun onBindView(holder: RecyclerView.ViewHolder?, data: String?, position: Int, size: Int) {
                        if (1 == getItemViewType(position)) {
                            holder?.itemView?.findViewById<StandardGSYVideoPlayer>(R.id.player)?.run {
                                isLooping = true
                                setUp(data, true, context.cacheDir, null)
                                backButton.visibility = View.GONE
                            }
                        } else {
                            holder?.itemView?.findViewById<AppCompatImageView>(R.id.image)?.run {
                                Glide.with(this).load(data).centerCrop().into(this)
                            }
                        }
                    }
                }, false)
                .addOnPageChangeListener(object : OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                    }

                    override fun onPageSelected(position: Int) {
                        player?.onVideoPause()
                        if (data[position].endsWith(".mp4")) {
                            // 视频
                            player = banner.adapter.viewHolder.itemView.findViewById<StandardGSYVideoPlayer>(R.id.player)
                            player?.run {
                                if (currentState == GSYVideoView.CURRENT_STATE_PAUSE) {
                                    onVideoResume()
                                } else {
                                    startPlayLogic()
                                }
                            }
                        } else {
                            player = null
                        }
                    }

                    override fun onPageScrollStateChanged(state: Int) {

                    }

                })
        banner.post {
            if (data[0].endsWith(".mp4")) {
                banner.adapter.viewHolder.itemView.findViewById<StandardGSYVideoPlayer>(R.id.player)?.startPlayLogic()
            }
        }
        banner.isAutoLoop(true)
        banner.setLoopTime(10000)
    }

    override fun initData() {}
    override fun getViewModelId(): Int {
        return BR.bannerViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.banner_activity
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }
}