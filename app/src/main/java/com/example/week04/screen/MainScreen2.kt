package com.example.lazycomposable.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.week04.component.TextLazyColumnFAB
import com.example.week04.screen.listSaver

@Composable
fun MainSceen2() {
    var dataList = rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    if (dataList.isEmpty()) {
        dataList.apply {
            repeat(10) {
                add((it + 1).toString())
            }
        }
    }

    Column {
        TextLazyColumnFAB(
            dataList,
            Modifier
                .fillMaxSize()
        )
    }
}