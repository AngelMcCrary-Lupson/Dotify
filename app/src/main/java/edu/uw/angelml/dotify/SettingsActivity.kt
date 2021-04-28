package edu.uw.angelml.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.angelml.dotify.databinding.ActivitySettingsBinding
import edu.uw.angelml.dotify.databinding.FragmentSettingsBinding

const val SONG_KEY = "currSong"
const val PLAY_COUNT = "playCount"
fun startSettingsActivity(context: Context, song: Song, playCount: String) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        putExtra(SONG_KEY, song)
        putExtra(PLAY_COUNT, playCount)
    })
}

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController by lazy { findNavController(R.id.navHost)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
            // This kept giving me errors
//        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
//        with(binding) {
//            navController.setGraph(R.navigation.nav_graph, intent.extras)
//        }

        val extras: Bundle? = intent.extras
        if (extras != null) {
             navController.setGraph(R.navigation.nav_graph, extras)
        }


        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
