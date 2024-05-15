package com.example.week04.week11.example02

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FetchWebPage() {
    var text by remember {
        mutableStateOf("")
    }

    var webSource by remember {
        mutableStateOf("")
    }

    val onValueChangeListener: (String) -> Unit = {
        text = it
    }

    val scrollState = rememberScrollState()

    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val onClick = {
        scope.launch {
            isLoading = true
//            var data = ""
//            CoroutineScope(Dispatchers.IO).async{
//                data = loadNetwork(URL(text))
//            }.await()
            var data = withContext(Dispatchers.IO) {
                loadNetwork(URL(text)) //network 접속하는 함수. main thread에서는 작동 안 함
            }
            webSource = data
            isLoading = false
        }
    }

    val pullRefreshState = rememberPullRefreshState( //material 최신 버전 추가
        refreshing = isLoading,
        onRefresh = { onClick() }
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = text,
                onValueChange = onValueChangeListener,
                modifier = Modifier.weight(3f)
            )
            Button(
                onClick = { onClick() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "fetch")
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .pullRefresh(pullRefreshState)
                .verticalScroll(scrollState)
        ) {
            if(webSource.isNotEmpty()) {
                Text(
                    text = webSource,
                    modifier = Modifier
                        .fillMaxWidth()
                        .sizeIn(maxHeight = 900.dp)
                )
            }
            PullRefreshIndicator(
                refreshing = isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

fun loadNetwork(url: URL): String {
    return try {
        val connect = (url.openConnection() as HttpURLConnection).apply {
            connectTimeout = 4000
            readTimeout = 1000
            requestMethod = "GET"
        }
        if (connect.responseCode == HttpURLConnection.HTTP_OK) {
            streamToString(connect.inputStream)
        } else {
            ""
        }
    } catch (e: Exception) {
        Log.e("error", "네트워크 요청 실패", e)
        ""
    }
}


fun streamToString(inputStream: InputStream): String {
    return try {
        BufferedReader(InputStreamReader(inputStream)).useLines { lines ->
            lines.fold("") { accumulator, line ->
                accumulator + line
            }
        }
    } catch (e: Exception) {
        Log.e("error", "읽기 실패")
        ""
    }
}