package com.example.week04.practice

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week04.example1.ScreenD1
import com.example.week04.model.VocDataViewModel
import com.example.week04.model.VocDataViewModel2
import com.example.week07.example1.HomeScreen1
import com.example.week07.example1.ScreenA1
import com.example.week07.example1.ScreenB1
import com.example.week07.example1.ScreenC1

sealed class Routes(val route:String){
    object Home:Routes("Home")
    object Word:Routes("Word")

    object Other:Routes("Other")




}
@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun PracticeNavi(navController: NavHostController, vocDataViewModel: VocDataViewModel = viewModel()) {
    NavHost(navController=navController, startDestination = "Home"){
        composable(route=Routes.Home.route){
            /*navController.currentBackStack.value.forEachIndexed{ index, navBackStackEntry ->
                Log.d("backstack", "$index : ${navBackStackEntry.destination.route}")
            }*/
            Home(navController)
        }

        composable(route=Routes.Word.route){
            WordList(navController, vocDataViewModel)
        }

        composable(route=Routes.Other.route){
            Other(navController)
        }



    }

}