package com.saashm.annoyingex.managers

import android.content.Context
import androidx.work.*
import com.saashm.annoyingex.backend.SendNotificationWorker
import java.util.concurrent.TimeUnit

class AnnoyUserManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingUser() {
        if(isRunning()) {
            stopWork()
        }
        val constraints = Constraints.Builder()
//            .setRequiresCharging(true)
             .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
//        val request = PeriodicWorkRequestBuilder<SendNotificationWorker>(20, TimeUnit.MINUTES)
//            .setInitialDelay(5, TimeUnit.SECONDS)
//            .setConstraints(constraints)
//            .addTag(WORK_REQUEST_TAG)
//            .build()
        val request = OneTimeWorkRequestBuilder<SendNotificationWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(WORK_REQUEST_TAG)
            .build()
        workManager.enqueue(request)
    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(WORK_REQUEST_TAG)
    }


    companion object {
        const val WORK_REQUEST_TAG = "WORK_REQUEST_TAG"
    }
}