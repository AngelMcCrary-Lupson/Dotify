package edu.uw.angelml.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.angelml.dotify.databinding.FragmentAboutBinding
import edu.uw.angelml.dotify.databinding.FragmentProfileBinding

class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAboutBinding.inflate(inflater)
        with(binding) {
            versionBuild.text = "Version Build: " + BuildConfig.VERSION_NAME
        }
        return binding.root
    }
}