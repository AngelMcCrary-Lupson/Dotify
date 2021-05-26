package edu.uw.angelml.dotify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

const val DOTIFY_APP_PREFS_KEY = "Dotify App Prefs"

class DotifyApplication: Application() {

    lateinit var userRepository: UserRepository
    lateinit var notificationManager: SongNotificationManager
    lateinit var refreshSongManager: RefreshSongManager
    lateinit var preferences: SharedPreferences
    val songManager: SongManager by lazy { SongManager() }

    override fun onCreate() {
        super.onCreate()

        refreshSongManager = RefreshSongManager(this)
        notificationManager = SongNotificationManager(this)
        userRepository = UserRepository()
        this.preferences = getSharedPreferences(DOTIFY_APP_PREFS_KEY, Context.MODE_PRIVATE)
    }
}