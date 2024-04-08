package com.example.week04.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextLazyColumnStickyHeader(dataList: MutableList<String>, modifier: Modifier = Modifier) {

    val groupedItems = dataList.groupBy { it[0] }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        groupedItems.forEach { (initial, models) ->

            stickyHeader {
                Text(
                    text = initial.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }
            items(items = models) { item ->
                TextCell(text = item, Modifier.background(Color.Cyan))
            }
        }
    }
}

@Preview
@Composable
private fun TextLazyColumnStickyHeaderPreview() {
    val dataList = (1..30).map { it.toString() }.toMutableList()
    TextLazyColumnStickyHeader(dataList = dataList, modifier = Modifier.fillMaxSize())
}
