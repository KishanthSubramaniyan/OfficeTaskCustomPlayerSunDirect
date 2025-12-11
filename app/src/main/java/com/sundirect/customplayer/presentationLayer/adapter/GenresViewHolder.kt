package com.sundirect.customplayer.presentationLayer.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.AdapterInflateViewdesigngeneresBinding
import com.sundirect.customplayer.datalayer.dto.Genre
import com.sundirect.customplayer.presentationLayer.Transfer

class GenresViewHolder(itemView: AdapterInflateViewdesigngeneresBinding, private val mListener : Transfer.Alpha<Int>? = null) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterInflateViewdesigngeneresBinding = itemView

    fun bind(list: List<Genre>) {
       mBinding.adapter = GenresAdapter(list, mListener)
       mBinding.executePendingBindings()
    }
}
