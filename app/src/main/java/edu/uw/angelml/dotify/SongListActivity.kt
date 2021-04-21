package edu.uw.angelml.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.angelml.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        // Add View Binding
        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        // Get List of Songs from API
        var songList= SongDataProvider.getAllSongs()
        with (binding) {
            val adapter = SongListAdapter(songList)
            // Change App Header
            title = getString(R.string.song_list_title)
            // Set Recycler View
            rvSongs.adapter = adapter
            // Hide Mini Player
            miniPlayer.visibility = ConstraintLayout.GONE
            // Enable Mini Player
            adapter.onSongClickListener = { position, songName, songArtist ->
                miniPlayerText.text = getString(R.string.mini_player_text, songName, songArtist)
                val selectedSong = songList[position]
                miniPlayer.visibility = ConstraintLayout.VISIBLE
                // Mini Player On Click - Launch Player Activity
                miniPlayer.setOnClickListener {
                    // Load Activity & send name & artist
                    navigateToPlayActivity(this@SongListActivity, selectedSong)
                }
            }

            // Shuffle Song List and Set the List to the Shuffled Version
            shuffleBtn.setOnClickListener{
                songList = songList.toMutableList().shuffled()
                adapter.shuffleSongs(songList)
            }
        }
    }
}