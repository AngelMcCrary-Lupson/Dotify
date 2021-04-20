package edu.uw.angelml.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallBack(private val newListOfSongs: List<Song>, private val oldListOfSongs: List<Song>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListOfSongs.size

    override fun getNewListSize(): Int = newListOfSongs.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newListOfSongs[newItemPosition]
        val oldSong = oldListOfSongs[oldItemPosition]
        return newSong.id == oldSong.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newListOfSongs[newItemPosition]
        val oldSong = oldListOfSongs[oldItemPosition]
        return newSong.title == oldSong.title && newSong.artist == oldSong.artist
    }
}