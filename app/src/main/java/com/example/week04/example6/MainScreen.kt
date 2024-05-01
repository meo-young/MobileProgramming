package com.example.week04.example6

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week04.example6.MainNavGraph
import com.example.week04.example6.WelcomeScreen

@Composable
fun rememberViewModelStoreOwner(): ViewModelStoreOwner {
    val context = LocalContext.current
    return remember(context) { context as ViewModelStoreOwner }
}

val LocalNavGraphViewModelStoreOwner =
    staticCompositionLocalOf<ViewModelStoreOwner> {
        error("Undefined")
    }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val navStoreOwner = rememberViewModelStoreOwner()

    CompositionLocalProvider(
        LocalNavGraphViewModelStoreOwner provides navStoreOwner
    ) {

        val navViewModel: NavViewModel =
            viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "20000 홍길동") }
                )
            },
            bottomBar = {
                if (navViewModel.loginStatus.value)
                    BottomNavigationBar(navController)
            }
        ) { contentPadding ->

            Column(modifier = Modifier.padding(contentPadding)) {// 적용 안 하면 오류 발생
                NavHost(
                    navController = navController,
                    startDestination = Routes.Login.route
                ) {
                    composable(Routes.Login.route) {
                        LoginScreen(navController)
                    }

                    composable(Routes.Welcome.route) {
                        WelcomeScreen(navController)
                    }

                    composable(Routes.Register.route) {
                        Register(navController)
                    }

                    MainNavGraph(navController)

                }
            }
        }
    }
}