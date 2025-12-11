package com.sundirect.customplayer.presentationLayer.viewmodels

import HomeAdapter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundirect.customplayer.datalayer.dto.HomeItem
import com.sundirect.customplayer.datalayer.dto.ContinueWatching
import com.sundirect.customplayer.datalayer.dto.Genre
import com.sundirect.customplayer.datalayer.dto.Hero
import com.sundirect.customplayer.datalayer.dto.HomeResponse
import com.sundirect.customplayer.datalayer.dto.Trending
import com.sundirect.customplayer.domainlayer.usecase.VideoRepositoryImpl
import com.sundirect.customplayer.presentationLayer.adapter.CarouselImageAdapterView
import com.sundirect.customplayer.presentationLayer.adapter.GenericPagerAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModels @Inject constructor(private val videoRepositoryImpl: VideoRepositoryImpl): ViewModel() {

    private val _homeData = MutableLiveData<HomeResponse>()
    val homeData: LiveData<HomeResponse> get() = _homeData

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList: LiveData<List<Hero>> = _heroList

    private val _heroIndex = MutableLiveData(0)
    val index: LiveData<Int> = _heroIndex

    private val _homeList = MutableLiveData<List<HomeItem>>()
    val homeList: LiveData<List<HomeItem>> = _homeList

    private val _homeListURL = MutableLiveData<Hero>()
    val homeListImage: LiveData<Hero> = _homeListURL

    private var currentIndex = 0

    init {

        loadHomeScreen()
    }

    private fun loadHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = videoRepositoryImpl.getHomeData()
            _homeData.postValue(response)
            _heroList.postValue(response.hero)
            loadHomeData(response.continueWatching,response.trending,response.genres)
        }
    }

    fun loadHomeData(
        continueWatching: List<ContinueWatching>,
        trending: List<Trending>,
        genres: List<Genre>
    ) {
        _homeList.postValue(listOf(
            HomeItem.ContinueWatchingView(continueWatching),
            HomeItem.TrendingView(trending),
            HomeItem.Genres(genres)
        ))
    }

    fun autoRotateHero() {
        viewModelScope.launch {
            while (isActive) {
                delay(30_000) // 30 seconds
                currentIndex = heroList.value?.size?.let { (currentIndex + 1) % it } ?: 0
                Log.e("status", "index value ==> $currentIndex")
                _heroIndex.postValue(currentIndex)
                _homeListURL.postValue(heroList.value?.get(currentIndex))
            }
        }
    }
}