package com.example.week04.example3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen2(navController: NavHostController) {

    val navViewModel: NavViewModel2 = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "${navViewModel.userID}님 환영합니다.",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}