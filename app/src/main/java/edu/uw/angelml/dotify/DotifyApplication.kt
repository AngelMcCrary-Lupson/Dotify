package edu.uw.angelml.dotify

import android.app.Application

class DotifyApplication: Application() {

    lateinit var userRepository: UserRepository
    lateinit var notificationManager: SongNotificationManager
    lateinit var refreshSongManager: RefreshSongManager
    val songManager: SongManager by lazy { SongManager() }

    override fun onCreate() {
        super.onCreate()

        refreshSongManager = RefreshSongManager(this)
        notificationManager = SongNotificationManager(this)
        userRepository = UserRepository()
    }
}