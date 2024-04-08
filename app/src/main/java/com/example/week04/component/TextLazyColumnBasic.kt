package com.example.week04.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextLazyColumnBasic(dataList: MutableList<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = dataList) {item->
            TextCell(text = item, Modifier.background(Color.Cyan))
        }
    }
}

@Preview
@Composable
private fun TextLazyColumnBasicPreview() {
    val dataList = (1..10).map { it.toString() }.toMutableList()
    TextLazyColumnBasic(dataList = dataList, modifier = Modifier.fillMaxSize())
}

