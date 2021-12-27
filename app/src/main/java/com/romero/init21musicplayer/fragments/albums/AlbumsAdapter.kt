package com.romero.init21musicplayer.fragments.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.AlbumRowBinding
import com.romero.init21musicplayer.models.AlbumModel
import com.romero.init21musicplayer.utils.Utils

class AlbumsAdapter(): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var albumsList = emptyList<AlbumModel>()

    // R.layout.album_row.xml - AlbumRowBinding
    class AlbumsViewHolder(val binding: AlbumRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {

        val binding = AlbumRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {

        holder.binding.apply {

            // img
            Utils.loadImg(albumsList[position].artUri, R.drawable.ic_music_note, artAlbum)

            nameAlbum.text = albumsList[position].name
            artistAlbum.text = albumsList[position].artist

        }

    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    fun setAlbums(albums: List<AlbumModel>) {

        albumsList = albums

        notifyDataSetChanged()

    }

}