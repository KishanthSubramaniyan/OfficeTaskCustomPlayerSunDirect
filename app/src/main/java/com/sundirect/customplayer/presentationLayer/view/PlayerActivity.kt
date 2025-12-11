package com.sundirect.customplayer.presentationLayer.view

import com.sundirect.customplayer.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.TimeBar
import com.sundirect.customplayer.NetworkConnectionLiveData
import com.sundirect.customplayer.databinding.LayoutCustomPlayerBinding
import com.sundirect.customplayer.presentationLayer.viewmodels.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerActivity : AppCompatActivity() {

    private lateinit var mBinding : LayoutCustomPlayerBinding

    private val viewModelPlayer : PlayerViewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this@PlayerActivity, R.layout.layout_custom_player)

        mBinding.viewmodels = viewModelPlayer
        mBinding.lifecycleOwner = this

    }

    @OptIn(UnstableApi::class)
    override fun onStart() {
        super.onStart()

        setupTimeBar()

        NetworkConnectionLiveData(context = this).observe(this) { if (!it) { viewModelPlayer.updateErrorMsg() } else { viewModelPlayer.updateSuccessMsg() } }

        viewModelPlayer.loadVideo("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")

        viewModelPlayer.currentPosition.observe(this) { mBinding.timeBar.setPosition(it) }
        viewModelPlayer.duration.observe(this) { mBinding.timeBar.setDuration(it) }

    }

    @OptIn(UnstableApi::class)
    private fun setupTimeBar() {
        mBinding.timeBar.addListener(object : TimeBar.OnScrubListener {
            override fun onScrubStart(tb: TimeBar, position: Long) {}
            override fun onScrubMove(tb: TimeBar, position: Long) {}

            override fun onScrubStop(tb: TimeBar, position: Long, canceled: Boolean) {
                viewModelPlayer.seekTo(position)
            }
        })
    }

}