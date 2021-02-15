package com.example.themoviedb.service

import android.app.*
import android.content.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.themoviedb.R
import com.example.themoviedb.ui.MainActivity
import com.google.firebase.messaging.*

class MyFireBaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("New Token: $token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(rm: RemoteMessage) {
        val title = rm.notification?.title.toString()
        val body = rm.notification?.body.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startOwnForeground(title, body)
        } else {
            startForeground(
                resources.getString(R.string.default_notification_channel_id).toInt(),
                getNotification(title, body)
            )
        }
    }

    private fun getNotification(title: String, body: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        return NotificationCompat.Builder(this)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    0
                )
            )
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startOwnForeground(title: String, body: String) {
        val channel = NotificationChannel(
            resources.getString(R.string.default_notification_channel_id),
            resources.getString(R.string.default_notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(channel)

        createNotification(title, body)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(title: String, body: String) {

        val notificationBuilder =
            NotificationCompat.Builder(
                this,
                resources.getString(R.string.default_notification_channel_id)
            )
        val notification = notificationBuilder
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    0
                )
            )
            .build()

        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.notify(0, notification)
    }
}