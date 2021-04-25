package edu.uw.angelml.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.uw.angelml.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val navController by lazy {findNavController()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            profileBtn.setOnClickListener{
                navController.navigate(R.id.profileFragment)
            }
            statsBtn.setOnClickListener{
                navController.navigate(R.id.statisticsFragment)
            }
            aboutBtn.setOnClickListener{
                navController.navigate(R.id.aboutFragment)
            }
        }

        return binding.root
    }
}