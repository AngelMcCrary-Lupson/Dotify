package edu.uw.angelml.dotify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.SongDataProvider
import kotlin.random.Random

private const val NEW_SONGS_CHANNEL_ID = "NEW_SONGS_CHANNEL_ID"

class SongNotificationManager (
        private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    // Notifications Disabled as default
    var isNotificationsEnabled = false
    init {
        // Initialize all channels
        initNotificationChannels()
    }

    fun publishNewSongNotification() {
        // Check to see if notifications are enabled
        if (!isNotificationsEnabled) {
            return
        }

        // Get random song to notify users about
        val randSong = SongDataProvider.getAllSongs().random()
        val artistName = randSong.artist
        val songTitle = randSong.title

        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val bundle = Bundle().apply {
                putParcelable("song", randSong)
            }
            putExtras(bundle)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)




        // Build information you want the notification to show
        val builder = NotificationCompat.Builder(context, NEW_SONGS_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_song)
                .setContentTitle("${artistName} just released a new song!!!")
                .setContentText("Listen to ${songTitle} now on Dotify")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(notificationManager) {
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        initNewSongChannel()
    }

    private fun initNewSongChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.new_songs)
            val descriptionText = context.getString(R.string.new_songs_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(NEW_SONGS_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

}