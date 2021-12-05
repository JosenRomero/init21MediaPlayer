package com.romero.init21musicplayer.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.romero.init21musicplayer.models.SongModel

object Utils {

    fun mediaPlayer(context: Context, song: SongModel): MediaPlayer {

        var mediaPlayer: MediaPlayer = MediaPlayer()

        mediaPlayer = MediaPlayer.create(context, Uri.parse(song.pathSong))

        mediaPlayer.isLooping = true // loop
        mediaPlayer.setVolume(0.5f, 0.5f) // volume

        return mediaPlayer
    }

    fun createTimeLabel(time: Int): String {

        val min = time / 1000 / 60
        val sec = time / 1000 % 60

        var timeLabel = "$min:"

        if(sec < 10) timeLabel += "0"

        timeLabel += sec

        return timeLabel

    }

}