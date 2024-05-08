package com.example.week04.week10

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = LocalDateTime.now().hour,
        initialMinute = LocalDateTime.now().minute
    )
    var showTimePicker by remember { mutableStateOf(false) }


    var msgState by remember{
        mutableStateOf("")
    }
    var currentMsg by remember {
        mutableStateOf("")
    }

    val postNotificationPermission=
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(key1 = true ){
        if(!postNotificationPermission.status.isGranted){
            postNotificationPermission.launchPermissionRequest()
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = msgState,
            onValueChange ={msgState = it},
            label = { Text("Message") }
        )
        Button(
            onClick = {
                currentMsg = msgState
                showDatePicker = true
            },
        ) {
            Text(text = "알림 설정")
        }

        // date picker dialog
        if (showDatePicker) { //onDismiss, confirmBtn 필수
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {

                    TextButton(
                        onClick = {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd").apply {
                                timeZone = TimeZone.getTimeZone("UTC") //java
                            }
                            var dateString: String = ""
                            datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                                dateString = dateFormat.format(Date(selectedDateMillis))
                            }
                            if (dateString.isBlank()) {
                                dateString = dateFormat.format(System.currentTimeMillis())
                            }
                            currentMsg += " ==> $dateString"
                            showDatePicker = false
                            showTimePicker = true
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                        }
                    ) { Text("Cancel") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        // time picker dialog
        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val hour = timePickerState.hour
                            val minute = timePickerState.minute
                            currentMsg += " ${hour}시 ${minute}분"
                            showTimePicker = false

                            coroutineScope.launch {
                                delay(2000)
                                makeNotification(context, msg = currentMsg)
                            }

                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePicker = false
                        }
                    ) { Text("Cancel") }
                }
            )
            {
                //  TimePicker(state = timePickerState) 얘는  시계
                TimeInput(state = timePickerState) // 시계 없는 버전
            }
        }
    }
}
