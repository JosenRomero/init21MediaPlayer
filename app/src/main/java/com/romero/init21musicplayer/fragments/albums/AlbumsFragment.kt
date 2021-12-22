package com.romero.init21musicplayer.fragments.albums

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.romero.init21musicplayer.databinding.FragmentAlbumsBinding
import com.romero.init21musicplayer.fragments.player.PlayerFragment
import com.romero.init21musicplayer.viewmodel.SongsViewModel

class AlbumsFragment : Fragment() {

    // FragmentAlbumsBinding - AlbumsFragment
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    var songsViewModel: SongsViewModel = PlayerFragment.songsViewModel

    private val albumsAdapter = AlbumsAdapter()

    @RequiresApi(Build.VERSION_CODES.R)
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

    @RequiresApi(Build.VERSION_CODES.R)
    private fun initRecyclerView() {
        getAllAlbums()
        binding.albumsRecyclerview.adapter = albumsAdapter
        binding.albumsRecyclerview.layoutManager = GridLayoutManager(activity, 2)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getAllAlbums() {

        songsViewModel.fetchAllAlbums(requireActivity().contentResolver).observe(viewLifecycleOwner, Observer { albums ->

            albumsAdapter.setAlbums(albums)

        })

    }

}