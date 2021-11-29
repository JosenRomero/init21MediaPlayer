package com.romero.init21musicplayer.fragments.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romero.init21musicplayer.databinding.SongRowBinding
import com.romero.init21musicplayer.models.SongModel

class SongsAdapter(): RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    private var songs = emptyList<SongModel>()

    // R.layout.song_row.xml - SongRowBinding
    class SongsViewHolder(val binding: SongRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {

        val binding = SongRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SongsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {

        holder.binding.apply {

            titleSong.text = songs[position].titleSong
            durationSong.text = songs[position].durationSong

        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun setSongs(songsList: List<SongModel>) {

        songs = songsList

        notifyDataSetChanged()

    }

}