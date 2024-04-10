package com.example.week04.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.week04.component.MainTitle
import com.example.week04.component.VocList
import com.example.week04.component.VocList2
import com.example.week04.component.VocList3

@Composable
fun MainScreen7(modifier: Modifier = Modifier){
    Column {
        MainTitle()
        VocList3()
    }
}