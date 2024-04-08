package com.example.week04.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.week04.component.TextLazyColumnBasic
import com.example.week04.component.TextLazyRow

@Composable
fun MainSceen1() {
    var dataList= rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    dataList.apply {
        repeat(100) {
            add((it + 1).toString())
        }
    }


    Column {
        TextLazyRow(
            dataList,
            Modifier
                .fillMaxWidth()
        )

        TextLazyColumnBasic(
            dataList,
            Modifier
                .fillMaxWidth()
        )
    }
}