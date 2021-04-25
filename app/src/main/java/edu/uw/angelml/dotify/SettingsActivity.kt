package edu.uw.angelml.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song

fun startSettingsActivity(context: Context) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java))
}

class SettingsActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.navHost)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
