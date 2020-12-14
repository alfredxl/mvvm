package com.xwl.mvvm.business.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.databinding.BannerActivityBinding
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
        banner.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun getItemViewType(position: Int): Int {
                return if (data[position].endsWith(".mp4")) 1 else 0
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = if (1 == viewType) {
                    LayoutInflater.from(this@BannerActivity).inflate(R.layout.video_item, parent, false)
                } else {
                    LayoutInflater.from(this@BannerActivity).inflate(R.layout.image_item, parent, false)
                }
                view.tag = viewType
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val viewType = getItemViewType(position)
                holder.itemView.tag = position
                holder.itemView.setTag(R.id.itemRoot, viewType)
                if (1 == viewType) {
                    holder.itemView.findViewById<StandardGSYVideoPlayer>(R.id.player)?.run {
                        isLooping = true
                        setUp(data[position], true, context.cacheDir, null)
                        backButton.visibility = View.GONE
                    }
                } else {
                    holder.itemView.findViewById<AppCompatImageView>(R.id.image)?.run {
                        Glide.with(this).load(data[position]).centerCrop().into(this)
                    }
                }
            }

            override fun getItemCount(): Int {
                return data.size
            }
        }
        banner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                player?.onVideoPause()
                banner.findViewWithTag<View>(position)?.let { itemRoot ->
                    val viewType = itemRoot.getTag(R.id.itemRoot) as Int
                    if (viewType == 1) {
                        player = itemRoot.findViewById(R.id.player)
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
            }
        })
        banner.offscreenPageLimit = data.size
        banner.post {
            if (data[0].endsWith(".mp4")) {
                banner.findViewWithTag<View>(0).findViewById<StandardGSYVideoPlayer>(R.id.player)?.startPlayLogic()
            }
        }
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