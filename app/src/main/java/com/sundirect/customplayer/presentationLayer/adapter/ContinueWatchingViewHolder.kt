package com.sundirect.customplayer.presentationLayer.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.AdapterInflateViewdesigncontinueBinding
import com.sundirect.customplayer.datalayer.dto.ContinueWatching
import com.sundirect.customplayer.presentationLayer.Transfer


class ContinueWatchingViewHolder(itemView: AdapterInflateViewdesigncontinueBinding, private val mListener : Transfer.Alpha<Int>? = null) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterInflateViewdesigncontinueBinding = itemView

    fun bind(list: List<ContinueWatching>) {
        mBinding.adapter = ContinueAdapter(list, mListener)
        mBinding.executePendingBindings()
    }
}
