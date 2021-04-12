package edu.uw.angelml.dotify

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private val randPlayCount: Int = (10000 - (Math.random() * 10000)).toInt()
    private lateinit var playCountView: TextView
    private var currPlayCount: Int = randPlayCount
    private var userNameChange: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Up Change User Name Button
        val changeUserBtn = findViewById<Button>(R.id.changeUserBtn)
        var userNameTextView = findViewById<TextView>(R.id.userNameTextView)
        var userNameEditText = findViewById<EditText>(R.id.userNameInput)
        userNameEditText.visibility = EditText.GONE
        changeUserBtn.setOnClickListener {
            if (!userNameChange) { // User name is text view
                userNameTextView.visibility = TextView.GONE
                userNameEditText.visibility = EditText.VISIBLE
                changeUserBtn.text = getString(R.string.apply_user)
                userNameChange = !userNameChange
            } else {
                val userInput = userNameEditText.text
                userNameTextView.text = userInput
                userNameTextView.visibility = TextView.VISIBLE
                userNameEditText.visibility = EditText.GONE
                changeUserBtn.text = getString(R.string.change_user)
                userNameChange = !userNameChange
            }
        }

        // Make Play Count a Random Number
        playCountView = findViewById<TextView>(R.id.playCountView)
        val playCountText = randPlayCount.toString() + " " + getString(R.string.plays)
        playCountView.text = playCountText

        // Set Up Playback Buttons
        val playBtn = findViewById<ImageView>(R.id.playBtn)
        playBtn.setOnClickListener{ playBtnPress() }

        val prevBtn = findViewById<ImageView>(R.id.backBtn)
        prevBtn.setOnClickListener{ prevBtnPress() }

        val forwardBtn = findViewById<ImageView>(R.id.forwardBtn)
        forwardBtn.setOnClickListener { forwardBtnPress() }

        // Extra Credit - Change Play Count Color
        val songCoverImg = findViewById<ImageView>(R.id.songCoverImg)
        songCoverImg.setOnLongClickListener{
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
        Toast.makeText(this, getString(R.string.skip_prev), Toast.LENGTH_SHORT).show()
    }

    fun forwardBtnPress () {
        Toast.makeText(this, getString(R.string.skip_next), Toast.LENGTH_SHORT).show()
    }

    fun playBtnPress () {
        currPlayCount++
        val newPlayCountText = currPlayCount.toString() + " " + getString(R.string.plays)
        playCountView.text = newPlayCountText
    }
}