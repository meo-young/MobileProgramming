package com.example.week04

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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

@Composable
fun MainScreen(){
    var counter1 by remember {
        mutableStateOf(0)
    }
    var counter2 by remember {
        mutableStateOf(0)
    }
    var counter3 by remember {
        mutableStateOf(0)
    }
    var counter4 by remember {
        mutableStateOf(0)
    }
    var counter5 by remember {
        mutableStateOf(0)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageWithSlot(img = R.drawable.img1) {
            ButtonWithIcon(counter = counter1) {
                counter1++
            }
        }
        ImageWithSlot(img = R.drawable.img2) {
            IconWithBadge(counter = counter2) {
                counter2++
            }
        }
        ImageWithSlot(img = R.drawable.img3) {
            ButtonWithIcon(counter = counter3) {
                counter3++
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