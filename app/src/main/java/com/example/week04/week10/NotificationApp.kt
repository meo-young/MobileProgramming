package com.example.week04.week10

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.week04.MainActivity
import com.example.week04.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val channelId = "MyTestChannel"
    val notificationId = 0
    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.header)
    val bigText = "This is my test notification in one line. Made it longer " +
            "by setting the setStyle property. " +
            "It should not fit in one line anymore, " +
            "rather show as a longer notification content."

    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }

    createNotificationChannel(channelId, context)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            "Notifications in Jetpack Compose",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 100.dp)
        )

        // simple notification button
        Button(onClick = {
            coroutineScope.launch {
                delay(2000)
                showSimpleNotification(
                    context,
                    channelId,
                    notificationId,
                    "Simple notification",
                    "This is a simple notification with default priority."
                )
            }
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Simple Notification")
        }

        // simple notification button with tap action
        Button(onClick = {
            showSimpleNotificationWithTapAction(
                context,
                channelId,
                notificationId+1,
                "Simple notification + Tap action",
                "This simple notification will open an activity on tap."
            )
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Simple Notification + Tap Action")
        }


        // large text notification button
        Button(onClick = {
            showLargeTextNotification(
                context,
                channelId,
                notificationId + 2,
                "My Large Text Notification",
                bigText
            )
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Large Text Notification")
        }

        // large text with big icon notification
        Button(onClick = {
            showLargeTextWithBigIconNotification(
                context,
                channelId,
                notificationId + 3,
                "Large Text with Big Icon Notification",
                "This is a large text notification with a big icon on the right.",
                myBitmap
            )
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Large Text + Big Icon Notification")
        }

        // big picture with auto hiding thumbnail notification
        Button(onClick = {
            showBigPictureWithThumbnailNotification(
                context,
                channelId,
                notificationId + 4,
                "Big Picture + Avatar Notification",
                "This is a notification showing a big picture and an auto-hiding avatar.",
                myBitmap
            )
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Big Picture + Big Icon Notification")
        }
    }
}

fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "MyTestChannel"
        val descriptionText = "My important test channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        with(NotificationManagerCompat.from(context)) {
            createNotificationChannel(channel)
        }
    //        val notificationManager: NotificationManager =
    //            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    //        notificationManager.createNotificationChannel(channel)
        //   }
    }

}

// shows notification with a title and one-line content text
fun showSimpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(notificationId, builder.build())
    }
}

// shows a simple notification with a tap action to show an activity
fun showSimpleNotificationWithTapAction(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationId, builder.build())
    }
}

// shows notification with large text
fun showLargeTextNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationId, builder.build())
    }
}

// shows notification with large text and a thumbnail image on the right
fun showLargeTextWithBigIconNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    largeIcon: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(largeIcon)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationId, builder.build())
    }
}

// shows notification with a big picture and an auto-hiding thumbnail
fun showBigPictureWithThumbnailNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    bigImage: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.baseline_access_alarm_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigImage)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(bigImage)
            //.bigLargeIcon(null)
        )
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationId, builder.build())
    }
}
