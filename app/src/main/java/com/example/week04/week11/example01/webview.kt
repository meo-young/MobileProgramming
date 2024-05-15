package com.example.week04.week11.example01

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.builtInZoomControls = true
                settings.defaultTextEncodingName = "utf-8"
            }
        },
        update = { webView ->
            webView.loadUrl("https://www.google.com")
            //webView.loadUrl("https://news.daum.net") //http"s"로 시작해야함
        }
    )
}