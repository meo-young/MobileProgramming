package com.example.week04

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lazycomposable.screen.MainSceen2
import com.example.week04.component.VocList
import com.example.week04.example2.LoginNavGraph
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

                    //AnnotatedClickableText()
                    val context = application
                    //val viewModel = viewModel<VocDataViewModel2>()
                    /*VocList(VocDataViewModel(context))*/
                    /*Column {
                        val navController = rememberNavController()
                        NavGraph1(navController)
                    }*/
                    Column {
                        val navController = rememberNavController()
                        PracticeNavi(navController, VocDataViewModel(context))
                    }

                }
            }
        }
    }
}
