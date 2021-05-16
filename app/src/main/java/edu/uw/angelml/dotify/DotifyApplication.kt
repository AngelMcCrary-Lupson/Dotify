package edu.uw.angelml.dotify

import android.app.Application

class DotifyApplication: Application() {

    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()

        userRepository = UserRepository()
    }
}