package edu.uw.angelml.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.angelml.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        // For extra credit - add to song player activity supportActionBar - 54:00 lecture 4-15
//        val songNames = listOf("Mundane", "Surreal", "Don't Take the Money")
        val songList= SongDataProvider.getAllSongs()
        with (binding) {
            val adapter = SongListAdapter(songList)
            title = getString(R.string.song_list_title)
            rvSongs.adapter = adapter

            adapter.onSongClickListener = { position, songName, songArtist ->
                Toast.makeText(this@SongListActivity, "You clicked as pos: $position", Toast.LENGTH_SHORT).show()
                miniPlayerText.text = songName + " - " + songArtist
            }

            shuffleBtn.setOnClickListener{
                adapter.shuffleSongs(songList.toMutableList().shuffled())
            }
        }
    }
}