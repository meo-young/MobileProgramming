package com.example.week04.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextLazyRow(dataList: MutableList<String>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(dataList) { item ->
            TextCell(text = item, Modifier.background(Color.Green))
        }
    }
}

@Preview
@Composable
private fun TextLazyRowPreview() {
    val dataList = (1..30).map { it.toString() }.toMutableList()
    TextLazyRow(dataList = dataList, modifier = Modifier.fillMaxWidth())
}
