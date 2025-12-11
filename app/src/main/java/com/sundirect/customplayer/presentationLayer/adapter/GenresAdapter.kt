package com.sundirect.customplayer.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.InflateContinuepostviewcarddesignBinding
import com.sundirect.customplayer.databinding.InflateGenrespostviewcarddesignBinding
import com.sundirect.customplayer.databinding.InflatePostviewcarddesignBinding
import com.sundirect.customplayer.datalayer.dto.ContinueWatching
import com.sundirect.customplayer.datalayer.dto.Genre
import com.sundirect.customplayer.datalayer.dto.Trending
import com.sundirect.customplayer.presentationLayer.Transfer

class GenresAdapter(
    private val list: List<Genre>,
    private val mListener : Transfer.Alpha<Int>? = null
) : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val binding: InflateGenrespostviewcarddesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Genre) {
            binding.continueWatchData = item
            binding.viewHolder = this
            binding.executePendingBindings()
        }

        fun showClicked() {
            mListener?.action(absoluteAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val mBinding : InflateGenrespostviewcarddesignBinding = InflateGenrespostviewcarddesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenreViewHolder(mBinding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
