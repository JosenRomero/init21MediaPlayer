package com.romero.init21musicplayer.fragments.player

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romero.init21musicplayer.R
import com.romero.init21musicplayer.databinding.FragmentPlayerBinding
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.utils.Utils
import com.romero.init21musicplayer.viewmodel.SongsViewModel

class PlayerFragment : Fragment() {

    // FragmentPlayerBinding - PlayerFragment
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    // PlayerFragmentArgs - PlayerFragment
    private val args by navArgs<PlayerFragmentArgs>()

    lateinit var currentSong: SongModel
    private var totalTimeSong: Int = 0

    private var handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable

    companion object {
        var songsViewModel: SongsViewModel = SongsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)

        // if you need to understand the args.currentSong
        // see my_nav.xml argument and
        // see SongsAdapter.kt itemRowLayout.setOnClickListener
        currentSong = args.currentSong

        setLayout()
        initMediaPlayer()
        initSeekBar()

        binding.btnPlay.setOnClickListener {
            if(songsViewModel.isPlaying.value == true) pauseMusic()
            else playMusic()
        }

        return binding.root
    }

    private fun setLayout() {

        // img
        Glide.with(this)
            .load(currentSong.artUri)
            .apply(RequestOptions())
            .placeholder(R.drawable.ic_music_note)
            .centerCrop()
            .into(binding.imgSong)

        binding.nameSong.text = currentSong.titleSong

        songsViewModel.isNowPlayingVisible.value = false

    }

    private fun initMediaPlayer() {

        songsViewModel.initPlayer(requireContext(), currentSong)

        totalTimeSong = songsViewModel.mediaPlayer!!.duration

        playMusic()

    }

    private fun initSeekBar() {

        binding.timeBarSong.max = totalTimeSong
        binding.remainingTimeLabel.text = Utils.createTimeLabel(totalTimeSong)

        binding.timeBarSong.setOnSeekBarChangeListener(
            object: SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {

                    if(changed) songsViewModel.mediaPlayer!!.seekTo(progress)

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            }
        )

        runnable = Runnable {

            val currentPos = songsViewModel.mediaPlayer!!.currentPosition

            // update timeBarSong
            binding.timeBarSong.progress = currentPos

            // update elapsedTimeLabel
            binding.elapsedTimeLabel.text = Utils.createTimeLabel(currentPos)

            // update remainingTimeLabel
            val remainingTimeLabel = "-" + Utils.createTimeLabel(totalTimeSong - currentPos)
            binding.remainingTimeLabel.text = remainingTimeLabel

            handler.postDelayed(runnable, 1000)

        }

        handler.postDelayed(runnable, 1000)

    }

    private fun playMusic() {
        binding.btnPlay.setBackgroundResource(R.drawable.ic_pause)
        songsViewModel.playMusic()
    }

    private fun pauseMusic() {
        binding.btnPlay.setBackgroundResource(R.drawable.ic_play)
        songsViewModel.stopMusic()
    }

}