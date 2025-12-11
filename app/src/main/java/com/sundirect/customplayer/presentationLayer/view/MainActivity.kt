package com.sundirect.customplayer.presentationLayer.view

import HomeAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sundirect.customplayer.R
import com.sundirect.customplayer.databinding.ActivityHomemainBinding
import com.sundirect.customplayer.datalayer.dto.Hero
import com.sundirect.customplayer.presentationLayer.Transfer
import com.sundirect.customplayer.presentationLayer.adapter.GenericPagerAdapter
import com.sundirect.customplayer.presentationLayer.fragment.DetailsFragment
import com.sundirect.customplayer.presentationLayer.viewmodels.HomeScreenViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Transfer.Alpha<Int> {

    private lateinit var mBinding : ActivityHomemainBinding

    private val viewModelsHome : HomeScreenViewModels by viewModels<HomeScreenViewModels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_homemain)

        mBinding.viewModels = viewModelsHome
        mBinding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()

        viewModelsHome.homeList.observe(this) {
            if (it.isNotEmpty()) {
                val homeAdapter = HomeAdapter(it, this@MainActivity)
                mBinding.rvHome.adapter = homeAdapter
            }
        }

        viewModelsHome.heroList.observe(this) { valueItem ->
            if (valueItem.isNotEmpty()) {
                val pagerAdapter = GenericPagerAdapter(
                    fragmentActivity = this,
                    items = valueItem,
                    createFragmentCallback = { hero ->
                        DetailsFragment.newInstance(0)
                    },
                )
                mBinding.imgHero.adapter = pagerAdapter
                viewModelsHome.autoRotateHero()
            }
        }

    }

    override fun action(alpha: Int) {
        startActivity(Intent(this@MainActivity, PlayerActivity::class.java))
    }
}