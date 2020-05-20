package com.saashm.annoyingex.managers

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyUserManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    fun startAnnoyingUser() {
        if(isRunning()) {
            stopWork()
        }
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = PeriodicWorkRequestBuilder<AnnoyUserWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(WORK_REQUEST_TAG)
            .build()
//        TO TEST:
//        val request = OneTimeWorkRequestBuilder<AnnoyUserWorker>()
//            .setInitialDelay(5, TimeUnit.SECONDS)
//            .setConstraints(constraints)
//            .addTag(WORK_REQUEST_TAG)
//            .build()
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