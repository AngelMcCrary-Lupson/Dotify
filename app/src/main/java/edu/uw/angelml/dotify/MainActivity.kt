package edu.uw.angelml.dotify

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private val randPlayCount: Int = (10000 - (Math.random() * 10000)).toInt()
    private lateinit var playCountView: TextView
    private var currPlayCount: Int = randPlayCount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playCountView = findViewById<TextView>(R.id.playCountView)
        val playCountText = randPlayCount.toString() + " plays"
        playCountView.text = playCountText

        val playBtn = findViewById<ImageView>(R.id.playBtn)
        playBtn.setOnClickListener{ playBtnPress() }

        val prevBtn = findViewById<ImageView>(R.id.backBtn)
        prevBtn.setOnClickListener{ prevBtnPress() }

        val forwardBtn = findViewById<ImageView>(R.id.forwardBtn)
        forwardBtn.setOnClickListener { forwardBtnPress() }

        // Extra Credit
        val songCoverImg = findViewById<ImageView>(R.id.songCoverImg)
        songCoverImg.setOnLongClickListener{
            // Toast.makeText(this, "Long click detected", Toast.LENGTH_SHORT).show()
            changePlayCountColor()
            true
        }
    }

    // Extra Credit
    fun changePlayCountColor () {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        playCountView.setTextColor(color)
    }

    fun prevBtnPress () {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun forwardBtnPress () {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun playBtnPress () {
        currPlayCount++
        val newPlayCountText = currPlayCount.toString() + " plays"
        playCountView.text = newPlayCountText
    }
}