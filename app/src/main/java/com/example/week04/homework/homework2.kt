package com.example.week04.homework

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.week04.R

@Composable
fun Img(img:Int, flag : Boolean){
    if(flag){
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun CheckboxWithText(text : String, index : Int, imgFlagModel : ImgFlagModel = viewModel()){
    var check by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = imgFlagModel.imglist[index].flag,
            onCheckedChange = {
                imgFlagModel.reDrawingFlag(index)
            }
        )
        Text(
            text = text
        )
    }
}

data class ImgFlag(var img:Int, var flag:Boolean)

class ImgFlagModel : ViewModel(){
    var imglist = mutableStateListOf<ImgFlag>()
        private set
    init{
        imglist.add(ImgFlag(R.drawable.img1, false))
    }
    fun reDrawingFlag(index:Int){
        imglist[index] = imglist[index].copy(flag = !imglist[index].flag)
    }
}
@Preview
@Composable
fun Closet(imgFlagModel : ImgFlagModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier.padding(top = 50.dp, bottom = 30.dp)
        ){
            Img(imgFlagModel.imglist[0].img, imgFlagModel.imglist[0].flag)
        }
        CheckboxWithText(text = "img1", index = 0)
    }
}