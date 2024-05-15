package com.example.week04.week10

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainScreen()
            }
        }
        composable(
            route = "Msg?msg={msg}", //내부용 링크
            deepLinks = listOf( //외부용 링크
                navDeepLink {
                    uriPattern = "myapp://greenjoahome.com/{msg}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("msg") {
                    type = NavType.StringType
                    defaultValue = "noMsg"
                }
            )
        ) {
            val msg = it.arguments?.getString("msg")
            MsgShow(msg = msg ?: "noMsg")
        }
    }
}