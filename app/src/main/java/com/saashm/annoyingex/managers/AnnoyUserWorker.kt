package com.saashm.annoyingex.managers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.saashm.annoyingex.AnnoyingExApp
import com.saashm.annoyingex.R

class AnnoyUserWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    override fun doWork(): Result {
        // sends notification
        val notificationManager = (applicationContext as AnnoyingExApp).notificationManager
        notificationManager.sendMessage()
        return Result.success()
    }
}