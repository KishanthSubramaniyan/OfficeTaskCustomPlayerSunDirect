package com.sundirect.customplayer.presentationLayer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class GenericPagerAdapter<T : Any>(
    fragmentActivity: FragmentActivity,
    private val items: List<T>,
    private val createFragmentCallback: (T) -> Fragment
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return createFragmentCallback(items[position])
    }

}

