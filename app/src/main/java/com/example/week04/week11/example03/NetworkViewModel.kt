package com.example.week04.week11.example03

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class NewsViewModel : ViewModel() {

    private val _newsList = mutableStateOf<List<NewsData>>(emptyList())
    val newsList: MutableState<List<NewsData>> = _newsList

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: MutableState<Boolean> = _isLoading

    fun fetchNews(url: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val fetchedNews = getNews(url)
                _newsList.value = fetchedNews
            } catch (e: Exception) {
                Log.e("error", "fetch 관련 오류 발생", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun getNews(url: String): List<NewsData> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(url).get()
        val headlines = doc.select("ul.list_newsissue>li>div.item_issue>div>strong.tit_g>a")
        headlines.map { NewsData(it.text(), it.absUrl("href")) }
    }
}