package com.example.week04.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TextLazyGrid(dataList: MutableList<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        //columns = GridCells.Adaptive(minSize = 100.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }){
            Text("Number")
        }
        items(dataList) { item ->
            TextCell(text = item, Modifier.background(Color.Green))
        }
    }
}


@Preview
@Composable
private fun TextLazyGridPreview() {
    val dataList = (1..30).map { it.toString() }.toMutableList()
    TextLazyGrid(dataList = dataList, modifier = Modifier.fillMaxSize())
}