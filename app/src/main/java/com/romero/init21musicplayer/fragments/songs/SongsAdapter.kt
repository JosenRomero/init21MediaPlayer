package com.romero.init21musicplayer.fragments.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.SongRowBinding
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.utils.Utils
import java.util.concurrent.TimeUnit

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
            durationSong.text = toMinAndSecond(songs[position].durationSong.toLong())

            // img
            Utils.loadImg(songs[position].artUri, R.drawable.ic_music_note, pathSong)

            itemRowLayout.setOnClickListener {

                val action = SongsFragmentDirections.actionSongsFragmentToPlayerFragment(indexCurrentSong = position)

                holder.itemView.findNavController().navigate(action)

            }

        }

    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun setSongs(songsList: List<SongModel>) {

        songs = songsList

        notifyDataSetChanged()

    }

    fun toMinAndSecond(milliseconds: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
            TimeUnit.MILLISECONDS.toSeconds(milliseconds)-TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(milliseconds)
            )
        )
    }

}