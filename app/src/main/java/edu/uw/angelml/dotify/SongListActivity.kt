package edu.uw.angelml.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.angelml.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        val songNames = listOf("Mundane", "Surreal", "Don't Take the Money")

        with (binding) {
            val adapter = SongListAdapter(songNames)
            rvSongs.adapter = adapter
        }
    }
}