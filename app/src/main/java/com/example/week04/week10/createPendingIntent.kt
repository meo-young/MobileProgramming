package com.example.week04.week10

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri

fun createPendingIntent(context: Context, msg:String): PendingIntent? {
    val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(
            Intent(Intent.ACTION_VIEW, "myapp://greenjoahome.com/$msg".toUri())
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            getPendingIntent(1234, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        else
            getPendingIntent(1234, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    return pendingIntent
}