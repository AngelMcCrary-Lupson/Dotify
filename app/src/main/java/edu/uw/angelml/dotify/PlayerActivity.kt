package edu.uw.angelml.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.Song
import edu.uw.angelml.dotify.databinding.ActivityPlayerBinding
import java.util.*

// Add Intent & Extras from Song List Activity
fun navigateToPlayActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java).apply {
        val bundle = Bundle().apply {
            putParcelable("song", song)
        }
        putExtras(bundle)
    }
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {
    private val randPlayCount: Int = (10000 - (Math.random() * 10000)).toInt()
    private var currPlayCount: Int = randPlayCount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // Extra Credit - Android Back Button in Header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Add View Binding
        val binding = ActivityPlayerBinding.inflate(layoutInflater).apply {setContentView(root)}

        with(binding) {
            // Set Song Name, Artist, & Album Art
            val selectSong: Song? = intent.extras?.getParcelable<Song>("song")
            if (selectSong != null) {
                songCoverImg.setImageResource(selectSong.largeImageID)
                songAuthorView.text = selectSong.artist
                songNameView.text = selectSong.title
            }

            // Start Settings Activity
            settingsBtn.setOnClickListener {
                // need to pass in curr song
                if (selectSong != null) {
                    startSettingsActivity(this@PlayerActivity, selectSong, currPlayCount.toString())
                }
            }

            // Make Play Count a Random Number
            val playCountText = randPlayCount.toString() + " " + getString(R.string.plays)
            playCountView.text = playCountText

            // Set Up Playback Buttons
            playBtn.setOnClickListener{ playBtnPress(playCountView) }
            backBtn.setOnClickListener{ prevBtnPress() }
            forwardBtn.setOnClickListener { forwardBtnPress() }

            // Extra Credit - Change Play Count Color
            songCoverImg.setOnLongClickListener{
                changePlayCountColor(playCountView)
                true
            }
        }
    }

    // Extra Credit - Android Back Button in Header
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    // Extra Credit
    fun changePlayCountColor (playCountView: TextView) {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        playCountView.setTextColor(color)
    }

    fun prevBtnPress () {
        Toast.makeText(this, getString(R.string.skip_prev), Toast.LENGTH_SHORT).show()
    }

    fun forwardBtnPress () {
        Toast.makeText(this, getString(R.string.skip_next), Toast.LENGTH_SHORT).show()
    }

    fun playBtnPress (playCountView: TextView) {
        currPlayCount++
        val newPlayCountText = currPlayCount.toString() + " " + getString(R.string.plays)
        playCountView.text = newPlayCountText
    }
}