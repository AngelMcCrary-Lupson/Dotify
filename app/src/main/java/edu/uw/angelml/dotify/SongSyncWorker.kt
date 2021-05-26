package edu.uw.angelml.dotify

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SongSyncWorker (
    private val context: Context,
    workerParameters: WorkerParameters
    ): CoroutineWorker(context, workerParameters) {

    private val application by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { application.notificationManager }

    override suspend fun doWork(): Result {

        Log.i("SongSyncWorker", "syncing songs now")
        songNotificationManager.publishNewSongNotification()

        return Result.success()
    }
}
