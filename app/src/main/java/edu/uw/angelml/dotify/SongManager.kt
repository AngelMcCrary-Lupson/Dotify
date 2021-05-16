package edu.uw.angelml.dotify

class SongManager {
    private var numSongsPlayed = 0

    fun onSongPlayed() {
        numSongsPlayed++
    }

    fun getSongsPlayed(): Int {
        return numSongsPlayed
    }
}