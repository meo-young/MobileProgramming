package com.example.week04.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week04.model.VocData
import com.example.week04.model.VocDataViewModel

@Composable
fun VocList(vocDataViewModel: VocDataViewModel = viewModel()){
    LazyColumn {
        itemsIndexed(vocDataViewModel.vocList){ index : Int, item :VocData->
            VocItem(vocData = item){
                vocDataViewModel.changeOpenStatus(index)
            }
        }
    }
}