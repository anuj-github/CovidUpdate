package com.graduateguy.covid.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.graduateguy.covid.repository.ICovidRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class UpdateDataWorker(
    context: Context,
    workerParameters: WorkerParameters): Worker(context, workerParameters), KoinComponent {
    override fun doWork(): Result {
        Log.d(TAG, "Work Started")
        val repository: ICovidRepository by inject()
        repository.loadGlobalSummary()
        Log.d(TAG, "Work Completed")
        return Result.success()
    }

    companion object{
        private const val WORK_NAME = "UpdateDataWorker"
        private const val TAG = "UpdateDataWorker"

        fun scheduleWork(context: Context) {
            Log.d(TAG, "Work Queued")
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            val periodicWorkRequest =
                PeriodicWorkRequest.Builder(
                    UpdateDataWorker::class.java, 1, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag(TAG)
                    .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
        }
    }

}