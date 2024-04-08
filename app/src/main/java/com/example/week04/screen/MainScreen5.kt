package com.example.week04.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week04.component.TextLazyGrid
import com.example.week04.screen.listSaver

@Composable
fun MainSceen5() {

    val dataList = rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    if (dataList.isEmpty()) {
        dataList.apply {
            repeat(10) {
                add((it + 1).toString())
            }
        }
    }

    Column {
        TextLazyGrid(
            dataList,
            Modifier
                .padding(10.dp)
                .fillMaxSize()
        )
    }
}
