package com.sundirect.customplayer.datalayer.dto

import com.sundirect.customplayer.presentationLayer.HomeViewType

sealed class HomeItem(val viewType: HomeViewType) {

    data class ContinueWatchingView(val items: List<ContinueWatching>) : HomeItem(HomeViewType.CONTINUE_WATCHING)

    data class TrendingView(val items: List<Trending>) : HomeItem(HomeViewType.TRENDING)

    data class Genres(val items: List<Genre>) : HomeItem(HomeViewType.GENRES)
}