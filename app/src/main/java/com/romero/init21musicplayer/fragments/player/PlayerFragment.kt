package com.romero.init21musicplayer.fragments.player

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    // FragmentPlayerBinding - PlayerFragment
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    // PlayerFragmentArgs - PlayerFragment
    private val args by navArgs<PlayerFragmentArgs>()

    private var mediaPlayer: MediaPlayer? = null

    lateinit var pathSong: String
    lateinit var titleSong: String
    lateinit var artUri: String

    private var isPlaying: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)

        // if you need to understand the args.currentSong
        // see my_nav.xml argument and
        // see SongsAdapter.kt itemRowLayout.setOnClickListener
        pathSong = args.currentSong.pathSong
        titleSong = args.currentSong.titleSong
        artUri = args.currentSong.artUri

        setLayout()
        initMediaPlayer()

        binding.btnPlay.setOnClickListener {
            if(isPlaying) pauseMusic()
            else playMusic()
        }

        return binding.root
    }

    private fun setLayout() {

        // img
        Glide.with(this)
            .load(artUri)
            .apply(RequestOptions())
            .placeholder(R.drawable.ic_music_note)
            .centerCrop()
            .into(binding.imgSong)

        binding.nameSong.text = titleSong

    }

    private fun initMediaPlayer() {

        mediaPlayer = MediaPlayer()
        mediaPlayer = MediaPlayer.create(requireContext(), Uri.parse(pathSong))

        mediaPlayer!!.isLooping = true // loop
        mediaPlayer!!.setVolume(0.5f, 0.5f) // volume
        binding.timeBarSong.max = mediaPlayer!!.duration // time of the song

        playMusic()

    }

    private fun playMusic() {
        binding.btnPlay.setBackgroundResource(R.drawable.ic_pause)
        isPlaying = true
        mediaPlayer!!.start()
    }

    private fun pauseMusic() {
        binding.btnPlay.setBackgroundResource(R.drawable.ic_play)
        isPlaying = false
        mediaPlayer!!.pause()
    }

}