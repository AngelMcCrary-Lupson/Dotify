package edu.uw.angelml.dotify

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.angelml.dotify.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private val navController by lazy {findNavController()}
    val safeArgs: SettingsFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

        val currSong = safeArgs.currSong
        val playCount = safeArgs.playCount
        with(binding) {
            profileBtn.setOnClickListener{
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToProfileFragment())
            }
            statsBtn.setOnClickListener{
//                navController.navigate(R.id.statisticsFragment)
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(playCount, currSong))
            }
            aboutBtn.setOnClickListener{
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToAboutFragment())
            }
        }

        return binding.root
    }
}