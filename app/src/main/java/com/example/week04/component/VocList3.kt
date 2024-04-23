package com.example.week04.component

import android.speech.tts.TextToSpeech
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week04.model.VocData
import com.example.week04.model.VocDataViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocList3(vocDataViewModel: VocDataViewModel = viewModel()){
    val context = LocalContext.current
    var ttsReady by rememberSaveable {
        mutableStateOf(false)
    }
    var tts : TextToSpeech? by rememberSaveable {
        mutableStateOf(null)
    }

    DisposableEffect(LocalLifecycleOwner.current) {
        tts = TextToSpeech(context){status ->
            if(status == TextToSpeech.SUCCESS){
                ttsReady = true
                tts!!.language = Locale.US
            }
        }
        onDispose { //lifecycle에 의해서 해제될 때 발동
            tts?.stop()
            tts?.shutdown()
        }
    }

    val speakWord = {vocData:VocData ->
        if(ttsReady){
            tts?.speak(vocData.word,TextToSpeech.QUEUE_ADD,null,null)
        }
    }

    val animateState = rememberLazyListState()
    val cScope = rememberCoroutineScope()

    val showBtn by remember {
        derivedStateOf {
            animateState.firstVisibleItemIndex > 0
        }
    }
    Box{
        LazyColumn(
            state = animateState
        ) {
            itemsIndexed(vocDataViewModel.vocList,
                key = {_,voc -> voc.word} // key가 단어로 되어있기 때문에 단어가 중복될 수 없음
            ){
                index : Int, item :VocData->
                val state = rememberDismissState( //swipe를 위해서는 상태 저장을 하는 변수 선언이 필요
                    confirmValueChange = {//원하는 방향으로 event가 발생했을 때 호출
                        if(it == DismissValue.DismissedToStart){ //왼쪽으로 swipe 하는 경우
                            vocDataViewModel.vocList.remove(item)
                            true
                        }
                        else
                            false
                    }
                )
                SwipeToDismiss(
                    state = state,
                    background = {
                        val color = when(state.dismissDirection){
                            DismissDirection.EndToStart -> Color.LightGray
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .background(color)
                        ){
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    dismissContent = {
                        VocItem(vocData = item){
                            speakWord(item)
                            vocDataViewModel.changeOpenStatus(index)
                        }
                    })
            }
        }
        AnimatedVisibility(visible = showBtn) {
            ScrollToTopButton {
                cScope.launch {
                    animateState.scrollToItem(0)
                }
            }
        }
    }

}