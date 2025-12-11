package com.sundirect.customplayer.presentationLayer.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import com.sundirect.customplayer.PlayerApplication.Companion.context
import jakarta.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private val _networkErrorMsg : MutableLiveData<String> = MutableLiveData("")
    val networkErrorMsg : LiveData<String> = _networkErrorMsg

    private val _networkSuccessMsg : MutableLiveData<String> = MutableLiveData("")
    val networkMsg : LiveData<String> = _networkSuccessMsg

    val _isPlaying = MutableLiveData(false)
    val playing : LiveData<Boolean> = _isPlaying

    val _isHide = MutableLiveData(false)
    val hide : LiveData<Boolean> = _isHide

    val _isLoading = MutableLiveData(false)
    val loading : LiveData<Boolean> = _isLoading

    val isBuffering = MutableLiveData(false)
    val currentPosition = MutableLiveData(0L)
    val duration = MutableLiveData(0L)

    val player: ExoPlayer? = context?.let { ExoPlayer.Builder(it).build() }

    private val handler = Handler(Looper.getMainLooper())

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (player?.isPlaying == true || player?.isLoading != true) {
                currentPosition.value = player?.currentPosition
                duration.value = player?.duration
            }
            handler.postDelayed(this, 500)
        }
    }

    init {

        observePlayer()
        handler.post(updateRunnable)
    }

    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    private fun observePlayer() {
        player?.addListener(object : Player.Listener {

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                // Hide loader when the video is actually playing
                _isLoading.value = false
                _isPlaying.value = isPlaying
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {

                    Player.STATE_BUFFERING -> {
                        _isLoading.value = true
                        isBuffering.value = true
                    }

                    Player.STATE_READY -> {
                        // Video is ready to play / resumed after buffering
                        _isLoading.value = false
                        isBuffering.value = false
                    }

                    Player.STATE_ENDED -> {
                        _isLoading.value = false
                        isBuffering.value = false
                    }

                    Player.STATE_IDLE -> {
                        _isLoading.value = false
                        isBuffering.value = false
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                _networkErrorMsg.value = "Playback Error: ${error.message}"
                _isLoading.value = false
                isBuffering.value = false
            }
        })
    }

    fun loadVideo(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    fun togglePlay() {

        _isHide.value = true

        if (player?.isPlaying == true) {
            player.pause()
        } else {
            _isLoading.value = true
            player?.play()
        }
    }

    fun updateErrorMsg() {
        _networkErrorMsg.value = "No Internet Connection. Please Check your Internet Connection!!"
    }

    fun updateSuccessMsg() {
        _networkSuccessMsg.value = "Internet Connection exists"
    }

    fun forward30() {
        player?.let {
            val newPos = it.currentPosition + 30_000L
            val finalPos = minOf(newPos, it.duration)
            it.seekTo(finalPos)
        }
    }

    fun reverse30() {
        player?.let {
            val newPos = it.currentPosition - 30_000L
            val finalPos = minOf(newPos, it.duration)
            it.seekTo(finalPos)
        }
    }

    override fun onCleared() {
        super.onCleared()
        player?.release()
    }
}
