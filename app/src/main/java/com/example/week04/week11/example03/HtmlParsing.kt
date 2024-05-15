package com.example.week04.week11.example03

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen03(newsViewModel: NewsViewModel = viewModel(), url: String) {
    val isLoading = newsViewModel.isLoading.value
    val newsList = newsViewModel.newsList.value
    val pullRefreshState = rememberPullRefreshState( //material 최신 버전 추가
        refreshing = isLoading,
        onRefresh = { newsViewModel.fetchNews(url) })

    LaunchedEffect(url) {
        newsViewModel.fetchNews(url)
    }

    Box (modifier = Modifier
        .pullRefresh(pullRefreshState)
    ){
        NewsList(list = newsList)
        PullRefreshIndicator(
            refreshing = newsViewModel.isLoading.value,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}