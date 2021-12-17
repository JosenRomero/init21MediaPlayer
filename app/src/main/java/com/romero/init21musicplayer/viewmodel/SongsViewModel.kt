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
    val isPlaying = MutableLiveData<Boolean>(false)
    val isNowPlayingVisible = MutableLiveData<Boolean>(false)
    var listSongs = emptyList<SongModel>()
    var indexCurrentSong: Int? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    fun fetchAllSongs(c: ContentResolver): MutableLiveData<RequestCall> {

        return songRepo.fetchAllSongs(c)

    }

    fun initPlayer(context: Context, index: Int) {
        val pathSong = listSongs[index].pathSong
        indexCurrentSong = index
        mediaPlayer = MediaPlayer()
        mediaPlayer = MediaPlayer.create(context, Uri.parse(pathSong))
    }

    fun playMusic() {
        isPlaying.value = true
        mediaPlayer!!.start()
    }

    fun stopMusic() {
        isPlaying.value = false
        mediaPlayer!!.pause()
    }

    fun nextSong() {
        indexCurrentSong = if(indexCurrentSong == listSongs.lastIndex) 0 else indexCurrentSong!! + 1
        val pathNextSong = listSongs[indexCurrentSong!!].pathSong
        newSong(pathNextSong)
    }

    fun preSong() {
        indexCurrentSong = if(indexCurrentSong == 0) listSongs.lastIndex else indexCurrentSong!! - 1
        val pathPreSong =  listSongs[indexCurrentSong!!].pathSong
        newSong(pathPreSong)
    }

    private fun newSong(pathSong: String) {
        mediaPlayer!!.stop()
        mediaPlayer!!.reset()
        mediaPlayer!!.setDataSource(pathSong)
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
    }

}