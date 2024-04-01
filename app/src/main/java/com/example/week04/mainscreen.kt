package com.example.week04

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.parcelize.Parcelize

@Composable
fun ImageWithSlot(img:Int, slotBtn:@Composable ()->Unit){
    Image(
        painter = painterResource(id = img),
        contentDescription = "이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
    slotBtn()
}

@Composable
fun ImageWithSlot(img:String, slotBtn:@Composable ()->Unit){
    AsyncImage(
        model = img,
        contentDescription = "이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
    slotBtn()
}

@Composable
fun ButtonWithIcon(counter : Int, onClick:()->Unit){
    Button(
        onClick={
            onClick()
        }
    ){
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red
        )
        if(counter > 0){
            Text("$counter")
        }
        else{
            Text("Like")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconWithBadge(counter : Int, onClick: () -> Unit){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(badge = {
            Badge{
                Text("$counter")
            }
        } ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.clickable {
                    onClick()
                }
            )
        }
    }
}
/*@Parcelize
data class ImgData(var img:Int, var counter:Int) :Parcelable*/
@Parcelize
data class ImgData(var img:Int, var counter:Int) :Parcelable
@Composable
fun MainScreen(){
    var img1 by rememberSaveable {
        mutableStateOf(ImgData(R.drawable.img1,10))
    }
    var img2 by rememberSaveable {
        mutableStateOf(ImgData(R.drawable.img2,20))
    }
    var img3 by rememberSaveable {
        mutableStateOf(ImgData(R.drawable.img3,30))
    }
    var counter4 by rememberSaveable {
        mutableStateOf(0)
    }
    var counter5 by rememberSaveable {
        mutableStateOf(0)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageWithSlot(img = img1.img) {
            ButtonWithIcon(counter = img1.counter) {
                img1 = img1.copy(counter = img1.counter+1)
            }
        }
        ImageWithSlot(img = img2.img) {
            IconWithBadge(counter = img2.counter) {
                img2 = img2.copy(counter = img2.counter+1)
            }
        }
        ImageWithSlot(img = img3.img) {
            ButtonWithIcon(counter = img3.counter) {
                img3 = img3.copy(counter = img3.counter+1)
            }
        }
        ImageWithSlot(img = R.drawable.img3) {
            ButtonWithIcon(counter = counter4) {
                counter4++
            }
        }
        ImageWithSlot(img = "https://i.namu.wiki/i/7IJj6iEgr4a41vfl5vOyUGtHAoC2ZQZABnuNua26JeAXjThMcrjJuul8E0CWmAfVB_m_8e535REXuU47IxU2Cg.webp") {
            ButtonWithIcon(counter = counter5) {
                counter5++
            }
        }

    }
}