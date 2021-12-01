package com.romero.init21musicplayer.fragments.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    // FragmentPlayerBinding - PlayerFragment
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    // PlayerFragmentArgs - PlayerFragment
    private val args by navArgs<PlayerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)

        // if you need to understand the args.currentSong
        // see my_nav.xml argument and
        // see SongsAdapter.kt itemRowLayout.setOnClickListener

        return binding.root
    }

}