package com.sundirect.customplayer.presentationLayer.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.AdapterInflateViewdesignBinding
import com.sundirect.customplayer.datalayer.dto.Trending
import com.sundirect.customplayer.presentationLayer.Transfer

class TrendingViewHolder(itemView: AdapterInflateViewdesignBinding, private val mListener : Transfer.Alpha<Int>? = null) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterInflateViewdesignBinding = itemView

    fun bind(list: List<Trending>) {
        val trendingAdapter = TrendingAdapter(list, mListener)
        mBinding.adapter = trendingAdapter
        mBinding.executePendingBindings()
    }
}
