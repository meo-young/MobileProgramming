package com.example.week04.week10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.week04.R

fun makeNotification(context: Context, msg: String) {
    val pendingIntent = createPendingIntent(context, msg)

    val channelId = "MyChanne"
    val channelName = "TimeCheckChannel"
    val notificationId = 0

    val notificationChannel =
        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

    val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(notificationChannel)

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle("일정 알람")
        .setContentText(msg)
        .setPriority(NotificationManager.IMPORTANCE_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(notificationId, notification) //퍼미션 추가
}