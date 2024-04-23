package com.example.week04.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextLazyColumnSwipeToDismiss(dataList: MutableList<String>, modifier: Modifier = Modifier) {

    val liststate = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            liststate.firstVisibleItemIndex > 0
        }
    }

    Box {
        LazyColumn(
            state = liststate,
            modifier = modifier,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(
                items = dataList,
                key = { index, item -> item.hashCode() }
            ) {
              index, item ->
                val state = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            dataList.remove(item)
                            true
                        } else
                            false
                    }
                )

                SwipeToDismiss(state = state,
                    background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.EndToStart -> Color.LightGray
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                        ) {
                            if(state.dismissDirection == DismissDirection.EndToStart){
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                )
                            }

                        }
                    },
                    dismissContent = {
                        TextCell(text = item, Modifier.background(Color.Green))
                    }
                )
            }
        }

        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton {
                scope.launch {
                    liststate.scrollToItem(0)
                }
            }
        }
    }
}

@Preview
@Composable
private fun TextLazyColumnSwipeToDismissPreview() {
    val dataList = (1..30).map { it.toString() }.toMutableList()
    TextLazyColumnSwipeToDismiss(dataList = dataList, modifier = Modifier.fillMaxSize())
}
