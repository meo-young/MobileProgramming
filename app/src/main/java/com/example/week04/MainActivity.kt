package com.example.week04

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lazycomposable.screen.MainSceen2
import com.example.week04.component.VocList
import com.example.week04.example2.LoginNavGraph
import com.example.week04.example4.ScaffoldEample
import com.example.week04.example6.MainScreen
import com.example.week04.model.VocDataViewModel
import com.example.week04.model.VocDataViewModel2
import com.example.week04.practice.PracticeNavi
import com.example.week04.screen.MainSceen1
import com.example.week04.screen.MainScreen7
import com.example.week04.screen.RandomColorButton
import com.example.week04.screen.RandomColorButton2
import com.example.week04.ui.theme.Week04Theme
import com.example.week04.week03.components.AnnotatedClickableText
import com.example.week07.example1.HomeScreen1
import com.example.week07.example1.NavGraph1
import java.io.File
import java.util.Scanner

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val navController = rememberNavController()
                        /*LoginNavGraph(navController = navController)*/
                        //ScaffoldEample()

                        //MainScreen(navController)
                        ShowIntent()
                    }

                }
            }
        }
    }
}

@Composable
fun ShowIntent(){
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick={
            val webpage = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(context, webIntent, null)
        }){
            Text("네이버")
        }
        Button(onClick={
            val messgage = Uri.parse("sms:010-2291-4153")
            val messageIntent = Intent(Intent.ACTION_SENDTO, messgage)
            messageIntent.putExtra("sms_body", "밥 먹자....")
            context.startActivity(messageIntent)
        }){
            Text("문자보내기")
        }
        Button(onClick={
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }){
            Text("맵")
        }
        Button(onClick={
            val number = Uri.parse("tel:010-1234-1234")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            context.startActivity(callIntent)
        }){
            Text("전화걸기")
        }
    }
}
