package edu.uw.angelml.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.angelml.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private val safeArgs: StatisticsFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStatisticsBinding.inflate(inflater)
        with(binding) {
            statsAlbumArt.setImageResource(safeArgs.currSong.largeImageID)
            playTime.text = safeArgs.currSong.title + " has been played " + safeArgs.playCount + " times"
        }
        return binding.root
    }
}