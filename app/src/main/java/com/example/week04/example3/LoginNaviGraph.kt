package com.example.week04.example3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week04.example3.LoginScreen2
import com.example.week04.example3.Register2
import com.example.week04.example3.WelcomeScreen2

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Welcome : Routes("Welcome")
    object Register : Routes("Register")
}

@Composable
fun rememberViewModelStoreOwner(): ViewModelStoreOwner {
    val context = LocalContext.current
    return remember(context) { context as ViewModelStoreOwner }
}

val LocalNavGraphViewModelStoreOwner =
    staticCompositionLocalOf<ViewModelStoreOwner> {
        error("Undefined")
    }

@Composable
fun LoginNavGraph2(navController: NavHostController) {

    val navStoreOwner = rememberViewModelStoreOwner()

    CompositionLocalProvider(
        LocalNavGraphViewModelStoreOwner provides navStoreOwner //블록 내에서 current 값인 navStoreOwner 제공
    ) {
        NavHost(navController = navController, startDestination = Routes.Login.route) {
            composable(route = Routes.Login.route) {
                LoginScreen2(navController)
            }

            composable(
                route = Routes.Welcome.route
            ) {
                WelcomeScreen2(navController)
            }

            composable(route = Routes.Register.route) { it ->
                Register2(navController)
            } //ViewModel 사용하면 인자전달 필요 없음
        }
    }
}

