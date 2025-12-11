package com.sundirect.customplayer.bindingUtil

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.annotation.OptIn
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import com.sundirect.customplayer.R
import com.sundirect.customplayer.datalayer.dto.Hero
import com.sundirect.customplayer.presentationLayer.adapter.CarouselImageAdapterView
import androidx.core.graphics.toColorInt
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.TimeBar
import com.sundirect.customplayer.presentationLayer.viewmodels.PlayerViewModel

object DataBindingAdapterUtil {

    @JvmStatic
    @BindingAdapter("showCarouselImage")
    fun showCarouselImageView(imageView: ImageView, imageUrl : String?) {
        imageUrl?.let {
            val request = ImageRequest.Builder(imageView.context)
                .data(it)
                .crossfade(true)
                .placeholder(R.drawable.loading_anim)
                .error(R.drawable.ic_broken_image)
                .target(imageView)
                .build()

            ImageLoader(imageView.context).enqueue(request)
        } ?: run {
            imageView.setImageResource(R.drawable.ic_broken_image)
        }
    }

    @JvmStatic
    @BindingAdapter("moveNextImage")
    fun showCarouselImageView(viewPager: ViewPager2, indexValue : Int) {
        viewPager.setCurrentItem(indexValue, true)
    }

    @JvmStatic
    @BindingAdapter("moveAdapterImage")
    fun showCarouselImageView(recyclerView: RecyclerView, indexValue : Int) {
        recyclerView.smoothScrollToPosition(indexValue)
    }

    @JvmStatic
    @BindingAdapter("player")
    fun showCarouselImageView(recyclerView: PlayerView, indexValue : Player) {
        recyclerView.player = indexValue
    }

    @JvmStatic
    @BindingAdapter("carouselAdapter")
    fun showCarouselImageView(recyclerView : RecyclerView, heroList : List<Hero>) {
        if (heroList.isEmpty()) return

        val adapterOldMaterialsJobsCheckList: CarouselImageAdapterView = (recyclerView.adapter as CarouselImageAdapterView)
        adapterOldMaterialsJobsCheckList.submitList(heroList)
    }

    @JvmStatic
    @BindingAdapter("showSnackbar")
    fun showSnackbar(view: View, message: String?) {
        if (!message.isNullOrEmpty()) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint("#D32F2F".toColorInt())
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
        }
    }

    @JvmStatic
    @BindingAdapter("showSuccessSnackbar")
    fun showSnackbarSuccess(view: View, message: String?) {
        if (!message.isNullOrEmpty()) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint("#388E3C".toColorInt())
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
        }
    }

}