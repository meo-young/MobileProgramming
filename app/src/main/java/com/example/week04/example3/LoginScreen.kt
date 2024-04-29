package com.example.week04.example3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.week04.example3.LocalNavGraphViewModelStoreOwner
import com.example.week04.example3.NavViewModel2

@Composable
fun LoginScreen2(navController: NavHostController) {

    val navViewModel: NavViewModel2 = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    //없으면 새로 만들어지고 있다면 기존의 reference 반환

    var userID by remember {
        mutableStateOf("")
    }

    var userPasswd by remember {
        mutableStateOf("")
    }

    var loginresult = navViewModel.checkInfo(userID, userPasswd) // false or true

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text="Home Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold)

        OutlinedTextField(value = userID,
            onValueChange = {userID =it},
            label = {Text("아이디")}
        )

        OutlinedTextField( value = userPasswd,
            onValueChange = { userPasswd = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {

            navViewModel.setUserInfo(userID, userPasswd) //navViewModel의 userid, passwd 갱신

            if(loginresult) {
                navController.navigate(Routes.Welcome.route)
            }
            else {
                navController.navigate(Routes.Register.route)
            }

        }){
            Text(text = "로그인")
        }
    }
}