package com.saashm.annoyingex.managers

import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.saashm.annoyingex.AnnoyingExApp
import com.saashm.annoyingex.MainActivity
import com.saashm.annoyingex.R
import kotlin.random.Random

class TextNotificationManager (private val context: Context) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createFunChannel()
    }

    fun sendMessage() {
        val content = (context as AnnoyingExApp).currentText
        val dealsIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(TEXT_ID, content)
        }

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, dealsIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, FUN_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            .setContentTitle("You Know Who Again")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createFunChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Fun Notifications"
            val descriptionText = "All Msgs from Annoying Ex"
            val importance = IMPORTANCE_DEFAULT
            val channel = NotificationChannel(FUN_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val FUN_CHANNEL_ID = "FUNCHANNELID"
        const val TEXT_ID = "TEXT_MESSAGE"
    }

}