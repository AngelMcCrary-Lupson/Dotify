package edu.uw.angelml.dotify

import android.app.Application

class DotifyApplication: Application() {

    lateinit var userRepository: UserRepository
    val songManager: SongManager by lazy { SongManager() }

    override fun onCreate() {
        super.onCreate()

        userRepository = UserRepository()
    }
}