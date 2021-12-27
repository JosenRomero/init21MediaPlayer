package com.romero.init21musicplayer.fragments.nowPlaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentNowPlayingBinding
import com.romero.init21musicplayer.fragments.player.PlayerFragment
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.utils.Utils
import com.romero.init21musicplayer.viewmodel.SongsViewModel

class NowPlayingFragment : Fragment() {

    // FragmentNowPlayingBinding - NowPlayingFragment
    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    var songsViewModel: SongsViewModel = PlayerFragment.songsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)

        initLayout()

        return binding.root
    }

    private fun initLayout() {

        songsViewModel.isNowPlayingVisible.observe(viewLifecycleOwner, Observer { isVisible ->
            if(isVisible && songsViewModel.mediaPlayer != null) setLayout()
            else binding.root.visibility = View.INVISIBLE
        })

        songsViewModel.isPlaying.observe(viewLifecycleOwner, Observer { isPlaying ->
            if(isPlaying) binding.btnPlayPlaying.setBackgroundResource(R.drawable.ic_pause)
            else binding.btnPlayPlaying.setBackgroundResource(R.drawable.ic_play)
        })

        binding.btnPlayPlaying.setOnClickListener {
            if(songsViewModel.isPlaying.value == true) songsViewModel.stopMusic()
            else songsViewModel.playMusic()
        }

    }

    private fun setLayout() {

        val currentSong: SongModel = songsViewModel.listSongs[songsViewModel.indexCurrentSong!!]

        binding.root.visibility = View.VISIBLE // nowPlayingFragment visible

        // img
        Utils.loadImg(currentSong.artUri, R.drawable.ic_music_note, binding.imgSongPlaying)

        binding.nameSongPlaying.text = currentSong.titleSong

    }

}