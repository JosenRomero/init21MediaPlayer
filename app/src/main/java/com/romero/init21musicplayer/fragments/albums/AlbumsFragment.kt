package com.romero.init21musicplayer.fragments.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentAlbumsBinding
import com.romero.init21musicplayer.fragments.player.PlayerFragment
import com.romero.init21musicplayer.models.AlbumModel
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.viewmodel.SongsViewModel
import java.util.ArrayList

class AlbumsFragment : Fragment() {

    // FragmentAlbumsBinding - AlbumsFragment
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    var songsViewModel: SongsViewModel = PlayerFragment.songsViewModel

    private val albumsAdapter = AlbumsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        setLayout()
        initRecyclerView()

        return binding.root
    }

    private fun setLayout() {

        songsViewModel.isNowPlayingVisible.value = true

    }

    private fun initRecyclerView() {
        binding.albumsRecyclerview.adapter = albumsAdapter
        binding.albumsRecyclerview.layoutManager = GridLayoutManager(activity, 2)

        val songs = ArrayList<SongModel>()
        songs.add(SongModel("title song", "3:11", "path example", "artUri example"))

        val listAlbums = ArrayList<AlbumModel>()
        listAlbums.add(AlbumModel("1", "name example", "artist example", "artUri exmple", songs))
        listAlbums.add(AlbumModel("2", "name example 2", "artist example 2", "artUri exmple 2", songs))
        listAlbums.add(AlbumModel("3", "name example 3", "artist example 3", "artUri exmple 3", songs))
        listAlbums.add(AlbumModel("4", "name example 4", "artist example 4", "artUri exmple 4", songs))
        listAlbums.add(AlbumModel("5", "name example 5", "artist example 5", "artUri exmple 5", songs))

        albumsAdapter.setAlbums(listAlbums)

    }

}