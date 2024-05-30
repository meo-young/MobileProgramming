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

    //Coroutine Scope내에서 실행하는 함수이므로 suspend로 선언
    private suspend fun getNews(url: String): List<NewsData> = withContext(Dispatchers.IO) {//main thread에서는 작업할 수 없음
        val doc = Jsoup.connect(url).get()
        val headlines = doc.select("ul.list_newsissue>li>div.item_issue>div>strong.tit_g>a")
        /*val list = mutableListOf<NewsData>()
        for(news in headlines){
            list.add(NewsData(news.text(), news.absUrl("href")))
        }*/
        headlines.map {news-> NewsData(news.text(), news.absUrl("href")) }
        //list
    }
}