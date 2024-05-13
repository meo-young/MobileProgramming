package com.example.week04

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lazycomposable.screen.MainSceen2
import com.example.week04.component.VocList
import com.example.week04.example2.LoginNavGraph
import com.example.week04.example4.ScaffoldEample
import com.example.week04.example6.MainScreen
import com.example.week04.model.VocDataViewModel
import com.example.week04.model.VocDataViewModel2
import com.example.week04.practice.PracticeNavi
import com.example.week04.screen.MainSceen1
import com.example.week04.screen.MainScreen7
import com.example.week04.screen.RandomColorButton
import com.example.week04.screen.RandomColorButton2
import com.example.week04.ui.theme.Week04Theme
import com.example.week04.week03.components.AnnotatedClickableText
import com.example.week04.week10.InstalledAppsList
import com.example.week04.week10.NavGraph
import com.example.week04.week10.NotificationApp
import com.example.week04.week10.getInstalledApps
import com.example.week04.week11.example01.WebViewScreen
import com.example.week07.example1.HomeScreen1
import com.example.week07.example1.NavGraph1
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
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
                    Column {
                        Text("202011255 김대영")
                        //val navController = rememberNavController()
                        /*LoginNavGraph(navController = navController)*/
                        //ScaffoldEample()

                        //MainScreen(navController)
                        //ShowIntent3()


                        /*val apps = getInstalledApps()
                        InstalledAppsList(apps = apps)*/

                        //NotificationApp()
                        
                        /*val navController = rememberNavController() 11주차 pending intent, deeplink
                        NavGraph(navController = navController)*/

                        WebViewScreen()
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShowIntent() {
    val context = LocalContext.current
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.CALL_PHONE)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val webpage = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(context, webIntent, null)
        }) {
            Text("네이버")
        }
        Button(onClick = {
            val messgage = Uri.parse("sms:010-2291-4153")
            val messageIntent = Intent(Intent.ACTION_SENDTO, messgage)
            messageIntent.putExtra("sms_body", "밥 먹자....")
            context.startActivity(messageIntent)
        }) {
            Text("문자보내기")
        }
        Button(onClick = {
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }) {
            Text("맵")
        }
        Button(
            onClick = {
                if (permissionState.status.isGranted)
                    makeCall(context)
                else
                    permissionState.launchPermissionRequest()
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("전화걸기")
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShowIntent2() {
    val context = LocalContext.current
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.CALL_PHONE)
    var showDialog by remember {
        mutableStateOf(false)
    }
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted)
                makeCall(context)
            else {
                if(!permissionState.status.shouldShowRationale)
                    showDialog = true
            }
        }
    LaunchedEffect(key1 = permissionState) {
        if(!permissionState.status.isGranted && !permissionState.status.shouldShowRationale) // 승인과 거부가 이뤄지지 않은 상태
            requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
    }
    if (showDialog) {
        if (permissionState.status.shouldShowRationale) { //사용자가 명시적으로 한 번 거절한 경우 true
            ShowCallPermissionRationale(
                onConfirm = {
                    showDialog = false
                    requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }else{
            ShowCallPermissionRationale(
                onConfirm = {
                    showDialog = false
                    // requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val webpage = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(context, webIntent, null)
        }) {
            Text("네이버")
        }
        Button(onClick = {
            val messgage = Uri.parse("sms:010-2291-4153")
            val messageIntent = Intent(Intent.ACTION_SENDTO, messgage)
            messageIntent.putExtra("sms_body", "밥 먹자....")
            context.startActivity(messageIntent)
        }) {
            Text("문자보내기")
        }
        Button(onClick = {
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }) {
            Text("맵")
        }
        Button(
            onClick = {
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("전화걸기")
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShowIntent3(){
    val context = LocalContext.current

    var permissions = arrayOf(
        android.Manifest.permission.CALL_PHONE,
        android.Manifest.permission.CAMERA
    )

    var permissionStates =
        rememberMultiplePermissionsState(permissions = permissions.toList())

    var showDialog by remember{
        mutableStateOf(false)
    }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if(permissionStates.allPermissionsGranted)
                Toast.makeText(context, "모든 권한이 승인됨",Toast.LENGTH_SHORT).show()
            else{
                if(!permissionStates.shouldShowRationale)
                    showDialog = true
            }

        }
    fun callPhonePermissionGranted() = permissionStates.permissions[0].status.isGranted
    fun cameraPermissionGranted() = permissionStates.permissions[1].status.isGranted

    LaunchedEffect(key1 = permissionStates) {
        if(!permissionStates.allPermissionsGranted && !permissionStates.shouldShowRationale)
            requestPermissionLauncher.launch(permissions)
    }

    if (showDialog) {
        if (permissionStates.shouldShowRationale) { //사용자가 명시적으로 한 번 거절한 경우 true
            ShowCallPermissionRationale(
                onConfirm = {
                    showDialog = false
                    requestPermissionLauncher.launch(permissions)
                },
                onDismiss = {
                    showDialog = false
                }
            )
        } else {
            ShowCallPermissionRationale(
                onConfirm = {
                    showDialog = false
                    // requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val webpage = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(context, webIntent, null)
        }) {
            Text("네이버")
        }
        Button(onClick = {
            val messgage = Uri.parse("sms:010-2291-4153")
            val messageIntent = Intent(Intent.ACTION_SENDTO, messgage)
            messageIntent.putExtra("sms_body", "밥 먹자....")
            context.startActivity(messageIntent)
        }) {
            Text("문자보내기")
        }
        Button(onClick = {
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }) {
            Text("맵")
        }
        Button(
            onClick = {
                if(callPhonePermissionGranted())
                    makeCall(context)
                else
                    requestPermissionLauncher.launch(permissions)
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("전화걸기")
        }
        Button(
            onClick = {
                if(cameraPermissionGranted())
                    cameraAction(context)
                else
                    requestPermissionLauncher.launch(permissions)
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("카메라")
        }
    }
}

fun cameraAction(context: Context){
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(cameraIntent)
}

@Composable
fun ShowCallPermissionRationale(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("권한 확인요청")
        },
        text = {
            Text("전화걸기 위해서는 CALL_PHONE 권한이 승인되어야 합니다")
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("권한승인")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("거부")
            }
        }
    )
}


fun makeCall(context: Context) {
    val number = Uri.parse("tel:010-1234-1234")
    val callIntent = Intent(Intent.ACTION_CALL, number)
    context.startActivity(callIntent)
}