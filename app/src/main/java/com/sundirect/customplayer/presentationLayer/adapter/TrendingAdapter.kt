package com.sundirect.customplayer.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.InflateContinuepostviewcarddesignBinding
import com.sundirect.customplayer.databinding.InflatePostviewcarddesignBinding
import com.sundirect.customplayer.datalayer.dto.ContinueWatching
import com.sundirect.customplayer.datalayer.dto.Trending
import com.sundirect.customplayer.presentationLayer.Transfer

class TrendingAdapter(
    private val list: List<Trending>,
    private val mListener : Transfer.Alpha<Int>? = null
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    inner class TrendingViewHolder(val binding: InflatePostviewcarddesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trending) {
            binding.continueWatchData = item
            binding.viewHolder = this
            binding.executePendingBindings()
        }

        fun showClicked() {
            mListener?.action(absoluteAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val mBinding : InflatePostviewcarddesignBinding = InflatePostviewcarddesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TrendingViewHolder(mBinding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
