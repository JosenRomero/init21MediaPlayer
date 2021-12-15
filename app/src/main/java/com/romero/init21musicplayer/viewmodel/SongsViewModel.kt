package com.romero.init21musicplayer.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romero.init21musicplayer.models.RequestCall
import com.romero.init21musicplayer.models.SongModel
import com.romero.init21musicplayer.repository.SongsRepository

class SongsViewModel(): ViewModel() {

    private val songRepo = SongsRepository()

    var mediaPlayer: MediaPlayer? = null
    var currentSong: SongModel? = null
    val isPlaying = MutableLiveData<Boolean>(false)
    val isNowPlayingVisible = MutableLiveData<Boolean>(false)

    @RequiresApi(Build.VERSION_CODES.Q)
    fun fetchAllSongs(c: ContentResolver): MutableLiveData<RequestCall> {

        return songRepo.fetchAllSongs(c)

    }

    fun initPlayer(context: Context, song: SongModel) {
        currentSong = song
        mediaPlayer = MediaPlayer()
        mediaPlayer = MediaPlayer.create(context, Uri.parse(song.pathSong))
    }

    fun playMusic() {
        isPlaying.value = true
        mediaPlayer!!.start()
    }

    fun stopMusic() {
        isPlaying.value = false
        mediaPlayer!!.pause()
    }

}