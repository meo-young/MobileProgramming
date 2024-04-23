package com.example.week04.example2

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.week04.example2.WelcomeScreen

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Welcome : Routes("Welcome")
    object Register : Routes("Register")
}

@Composable
fun LoginNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Login") { //owner를 지정하지 않으면 backstackentry가 자동으로 지정되므로, activity를 owner로 지정하는 과정 필요.
        composable(route = Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(
            route = Routes.Welcome.route + "/{userID}",
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType
                }
            )
        ) {
            WelcomeScreen(
                navController,
                it.arguments?.getString("userID")
            )
        }

        composable(route= Routes.Register.route+"?userid={userID}&userpasswd={userPasswd}", //공백이 있으면 안 됨
            arguments = listOf(
                navArgument(name = "userID"){
                    type = NavType.StringType
                    defaultValue = "user"
                },
                navArgument(name = "userPasswd"){
                    type = NavType.StringType
                    nullable = true
                }
            )
            ){
            Log.d("userid",it.arguments?.getString("userID")!!)
            Register(
                navController,
                it.arguments?.getString("userID"),
                it.arguments?.getString("userPasswd")
                )
        }
    }

}