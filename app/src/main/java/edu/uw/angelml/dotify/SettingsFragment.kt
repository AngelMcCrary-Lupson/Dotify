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

const val NOTIFICATIONS_ENABLED_PREF_KEY = "notifications_enabled"

class SettingsFragment : Fragment() {
    private lateinit var dotifyApplication: DotifyApplication
    private val navController by lazy {findNavController()}
    val safeArgs: SettingsFragmentArgs by navArgs()
    private val songNotificationManager: SongNotificationManager by lazy { dotifyApplication.notificationManager }
    private val preferences by lazy { dotifyApplication.preferences }

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

            // Set switch to previous settings from shared preferences
            val isSwitched = preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false)
            switchNotifications.isChecked = isSwitched
            songNotificationManager.isNotificationsEnabled = isSwitched

            // Testing logs shared preference
            // Log.i("Song", "Prefs is : " + preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false))

            switchNotifications.setOnCheckedChangeListener {_, isChecked ->
                // Toggle Notifications
                songNotificationManager.isNotificationsEnabled = switchNotifications.isChecked

                // Saving the value in preferences whether the switch is on or not
                val editor = preferences.edit()
                editor.putBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, isChecked)
                editor.apply()

                // Testing logs shared preference
                // Log.i("Song", "Saving Prefs to : " + preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false))

                // Toggles Notifications
                if (isChecked) {
                    Toast.makeText(context, "Push Notifications enabled", Toast.LENGTH_SHORT).show()
                    dotifyApplication.refreshSongManager.startRefreshSongsPeriodically()
                } else {
                    Toast.makeText(context, "Push Notifications disabled", Toast.LENGTH_SHORT).show()
                    dotifyApplication.refreshSongManager.stopPeriodicallyRefreshing()
                }
            }
        }

        return binding.root
    }
}