package edu.uw.angelml.dotify

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val SONG_SYNC_WORK_TAG = "SONG_SYNC_WORK_TAG"

class RefreshSongManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun refreshSongs() {

        val request = OneTimeWorkRequestBuilder<SongSyncWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()
                )
                .addTag(SONG_SYNC_WORK_TAG)
                .build()

        workManager.enqueue(request)
    }

    fun startRefreshSongsPeriodically() {
        if (isSongSyncRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongSyncWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()
                )
                .addTag(SONG_SYNC_WORK_TAG)
                .build()

        workManager.enqueue(request)

    }

    fun stopPeriodicallyRefreshing() {
        workManager.cancelAllWorkByTag(SONG_SYNC_WORK_TAG)
    }

    private fun isSongSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_SYNC_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }




}