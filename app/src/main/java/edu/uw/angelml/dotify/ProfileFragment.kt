package edu.uw.angelml.dotify

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.uw.angelml.dotify.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var dotifyApplication: DotifyApplication
    private val userRepository by lazy { dotifyApplication.userRepository }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Getting App context
        val context = this.context
        dotifyApplication = context?.applicationContext as DotifyApplication

        binding = FragmentProfileBinding.inflate(inflater)

        with(binding) {
            lifecycleScope.launch {
                runCatching {
                    val user = userRepository.getUserInfo()
                    username.text = user.username
                    email.text = user.firstName + user.lastName + "@hogwarts.edu"
                    // Use Coil to load the Profile Image
                    profilePic.load(user.profilePicURL)
                    // Hide Error
                    profileError.visibility = LinearLayout.GONE
                    // Shows number of songs listened to during the app session from SongManager
                    val songsPlayed = dotifyApplication.songManager.getSongsPlayed()
                    songsListened.text = "Total Songs Played This Session: " + songsPlayed
                }.onFailure {
                    profileError.visibility = LinearLayout.VISIBLE
                }
            }
        }

        return binding.root
    }
}