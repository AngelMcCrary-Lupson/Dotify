package edu.uw.angelml.dotify

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uw.angelml.dotify.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val dotifyApplication: DotifyApplication by lazy { application as DotifyApplication }
    private val userRepository by lazy { dotifyApplication.userRepository }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        with(binding) {
            lifecycleScope.launch {
                kotlin.runCatching {
                    val user = userRepository.getUserInfo()
                    username.text = user.username
                    email.text = user.firstName + user.lastName + "@hogwarts.edu"
                    // do something with glide to show img
                }.onFailure { 
                    username.text = "An error occured when fetching your profile"
                }
            }
        }

        return binding.root
    }
}