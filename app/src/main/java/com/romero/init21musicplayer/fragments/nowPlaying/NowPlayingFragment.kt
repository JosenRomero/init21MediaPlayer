package com.romero.init21musicplayer.fragments.nowPlaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentNowPlayingBinding

class NowPlayingFragment : Fragment() {

    // FragmentNowPlayingBinding - NowPlayingFragment
    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)

        return binding.root
    }

}