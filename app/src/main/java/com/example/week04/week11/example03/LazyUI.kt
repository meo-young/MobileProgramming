package com.example.week04.week11.example03

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NewsData(
    var title: String,
    var newsUrl: String
)

@Composable
fun NewsList(list: List<NewsData>) {
    LazyColumn {
        items(list) { item ->
            NewsItem(item)
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
fun NewsItem(news: NewsData) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.newsUrl))
            context.startActivity(intent)
        }
    ) {
        Text(news.title, fontSize = 20.sp)
    }
}