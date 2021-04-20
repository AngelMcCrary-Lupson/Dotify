package edu.uw.angelml.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.angelml.dotify.databinding.ItemSongBinding


class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            songName.text = song.title
            artistName.text = song.artist
            albumArt.setImageResource(song.smallImageID) //Doesn't show colored squares
        }
    }

    // Extra Credit - Shuffle List with DiffUtil
    fun shuffleSongs(newListOfSongs: List<Song>) {
        val callback = SongDiffCallBack(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        this.listOfSongs = newListOfSongs
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = listOfSongs.size
}