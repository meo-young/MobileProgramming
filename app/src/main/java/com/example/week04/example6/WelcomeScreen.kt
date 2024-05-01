package com.example.week04.example6

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.week04.example6.LocalNavGraphViewModelStoreOwner
import com.example.week04.example6.NavViewModel
import com.example.week04.example6.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navController: NavHostController) {

    val navViewModel: NavViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        LaunchedEffect(key1 = Unit) { //시작할 때 화면 전환 효과 발생. 권한 체크는 이걸로 ~! 지금은 Unit. 즉 상수이므로 1번만 실행이 됨
            delay(2000) // delay는 suspend 함수이므로 coroutine에서 실행할 것
            navViewModel.loginStatus.value = true
        }
        if(navViewModel.loginStatus.value){
            navController.navigate(Routes.Main.route){
                popUpTo(Routes.Login.route){
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
        //DisposableEffect 는 제거될 때 onDispose가 있어야함
        //SideEffect는 recomposition 될 때
        //rememberCoroutineScope는 이벤트가 발생할 때
        Button(onClick = {
            navViewModel.loginStatus.value = true
            coroutineScope.launch { // 컴포저블 함수 외에서 사용 가능. OnClick은 컴포저블이 아니므로 가능
                delay(2000)
                navController.navigate(Routes.Main.route)
            }
        }) {
            Text("메인화면")
        }
    }
}