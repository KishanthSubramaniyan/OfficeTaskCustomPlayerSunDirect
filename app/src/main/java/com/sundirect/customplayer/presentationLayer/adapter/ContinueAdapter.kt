package com.sundirect.customplayer.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.InflateContinuepostviewcarddesignBinding
import com.sundirect.customplayer.datalayer.dto.ContinueWatching
import com.sundirect.customplayer.presentationLayer.Transfer

class ContinueAdapter(
    private val list: List<ContinueWatching>,
    private val mListener : Transfer.Alpha<Int>? = null
) : RecyclerView.Adapter<ContinueAdapter.ContinueWatchingViewHolder>() {

    inner class ContinueWatchingViewHolder(val binding: InflateContinuepostviewcarddesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContinueWatching) {
            binding.continueWatchData = item
            binding.viewHolder = this
            binding.executePendingBindings()
        }

        fun showClicked() {
            mListener?.action(absoluteAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinueWatchingViewHolder {
        val mBinding : InflateContinuepostviewcarddesignBinding = InflateContinuepostviewcarddesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContinueWatchingViewHolder(mBinding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ContinueWatchingViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
