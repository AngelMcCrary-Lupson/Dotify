package edu.uw.angelml.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.angelml.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

    private lateinit var selectedSong: Song
    private var selectedSongPosition = -1
    private var miniPlayerState = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelable("currSong", selectedSong)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Loading Previous State of Selected Song
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                val savedSong = getParcelable<Song>("currSong")
                if (savedSong != null) {
                    selectedSong = savedSong
                    miniPlayerState = true
                }
            }
        }


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
            // Initializes Mini Player with Previous State
            if (miniPlayerState) {
                var songName = selectedSong.title
                var songArtist = selectedSong.artist
                miniPlayerText.text = getString(R.string.mini_player_text, songName, songArtist)
                miniPlayer.visibility = ConstraintLayout.VISIBLE
                // Mini Player On Click - Launch Player Activity
                miniPlayer.setOnClickListener {
                    // Load Activity & send name & artist
                    navigateToPlayActivity(this@SongListActivity, selectedSong)
                }
            } else {
                // Hide Mini Player
                miniPlayer.visibility = ConstraintLayout.GONE
            }
            // Enable Mini Player
            adapter.onSongClickListener = { position, songName, songArtist ->
                miniPlayerText.text = getString(R.string.mini_player_text, songName, songArtist)
                selectedSong = songList[position]
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