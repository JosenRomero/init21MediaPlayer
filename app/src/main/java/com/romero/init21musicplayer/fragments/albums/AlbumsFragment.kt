package com.romero.init21musicplayer.fragments.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentAlbumsBinding
import com.romero.init21musicplayer.fragments.player.PlayerFragment
import com.romero.init21musicplayer.viewmodel.SongsViewModel

class AlbumsFragment : Fragment() {

    // FragmentAlbumsBinding - AlbumsFragment
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    var songsViewModel: SongsViewModel = PlayerFragment.songsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        setLayout()

        return binding.root
    }

    private fun setLayout() {

        songsViewModel.isNowPlayingVisible.value = true

    }

}