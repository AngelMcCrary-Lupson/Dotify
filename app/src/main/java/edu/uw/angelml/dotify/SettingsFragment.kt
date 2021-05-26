package edu.uw.angelml.dotify

import android.R
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.angelml.dotify.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private lateinit var dotifyApplication: DotifyApplication
    private val navController by lazy {findNavController()}
    val safeArgs: SettingsFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Getting App context
        val context = this.context
        dotifyApplication = context?.applicationContext as DotifyApplication

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

//            dotifyTaskManager.isNotificationsEnabled = switchNotifications.isChecked


            switchTasks.setOnCheckedChangeListener {_, isChecked ->
                // Toggle Notifications
//                dotifyTaskManager.isTasksEnabled = switchTasks.isChecked

                if (isChecked) {
                    Toast.makeText(context, "Background Tasks enabled", Toast.LENGTH_SHORT).show()
                    dotifyApplication.refreshSongManager.startRefreshSongsPeriodically()
                } else {
                    Toast.makeText(context, "Background Tasks disabled", Toast.LENGTH_SHORT).show()
                    dotifyApplication.refreshSongManager.stopPeriodicallyRefreshing()
                }
            }
        }

        return binding.root
    }
}