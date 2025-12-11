package com.sundirect.customplayer.presentationLayer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sundirect.customplayer.databinding.FragmentCarouselviewdetailsBinding
import com.sundirect.customplayer.presentationLayer.viewmodels.HomeScreenViewModels
import kotlin.getValue

class DetailsFragment : Fragment()  {

    private lateinit var mBinding: FragmentCarouselviewdetailsBinding

    private val viewModels : HomeScreenViewModels by activityViewModels<HomeScreenViewModels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentCarouselviewdetailsBinding.inflate(inflater,container,false)

        mBinding.viewModels = viewModels
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(index: Int): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundleOf("index" to index)
            return fragment
        }
    }

}