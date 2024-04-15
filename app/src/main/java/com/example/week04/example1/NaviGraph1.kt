package com.example.week07.example1

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week04.example1.ScreenD1

sealed class Routes(val route:String){
    object Home:Routes("Home")
    object A:Routes("A")
    object B:Routes("B")
    object C:Routes("C")
    object D:Routes("D")
    object E:Routes("E")


}
@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun NavGraph1(navController: NavHostController) {

    NavHost(navController=navController, startDestination = "Home"){

        composable(route=Routes.Home.route){
            navController.currentBackStack.value.forEachIndexed{ index, navBackStackEntry ->
                Log.d("backstack", "$index : ${navBackStackEntry.destination.route}")
            }
            HomeScreen1(navController)
        }

        composable(route=Routes.A.route){
            ScreenA1(navController)
        }

        composable(route=Routes.B.route){
            ScreenB1(navController)
        }

        composable(route=Routes.C.route){
            ScreenC1(navController)

        }

        composable(route=Routes.D.route){
            ScreenD1(navController)
        }
    }

}