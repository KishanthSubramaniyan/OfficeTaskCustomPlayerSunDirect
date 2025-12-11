package com.sundirect.customplayer.presentationLayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.databinding.InflateviewImageswipeBinding
import com.sundirect.customplayer.datalayer.dto.Hero

class CarouselImageAdapterView : ListAdapter<Hero, CarouselImageAdapterView.ViewHolder>(CustomDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = InflateviewImageswipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailPair = getItem(position)
        holder.bindData(detailPair)
    }

    class ViewHolder(private val mBinding: InflateviewImageswipeBinding) :
        RecyclerView.ViewHolder(mBinding.root)  {

        fun bindData(detail: Hero) {
            mBinding.heroImage = detail
            mBinding.executePendingBindings()
        }
    }

    class CustomDiffUtils : DiffUtil.ItemCallback<Hero>()
    {
        override fun areItemsTheSame(oldItem: Hero , newItem: Hero): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return false
        }
    }
}